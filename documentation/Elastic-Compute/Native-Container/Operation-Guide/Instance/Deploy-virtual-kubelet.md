# virtual-kubelet 部署  
Virtual-Kubetlet是一种开源的Kubernetes Kubelet部署方法，为其他API连接Kubernetes集群提供了虚Kubelet的实现方案。目前京东云基于Virtual-Kubelet V0.5.1版本开发，提供了原生容器Pod连接到Kubernetes集群方案。  

## virtual-kubelet 自动化部署  

京东云提供了[Virual kubelet自动化部署脚本][1]，在可以连接到集群的终端下载并执行自动化部署脚本，以CentOS 7.4 64位操作系统为例，执行步骤如下：  
1. 下载并解压缩自动化部署脚本到本地：  
`   
wget http://kubernetes.oss.cn-north-1.jcloudcs.com/virtual-kubelet/jdcloud-virtual-kubelet.tar.gz  
`   
`  
tar -zxvf jdcloud-virtual-kubelet.tar.gz  
`  
2. 进入jdcloud-virtual-kubelet目录并执行自动化部署脚本：   
`  
sh virtual-kubelet.sh 114.**.**.93:6443 cn-****-2a,cn-****-2b g.n2  
`   
脚本运行的参数说明如下：  
- 集群服务端点：virtual-kubelet连接的Kubernetes集群提供的服务端点，您可在Kubernetes集群详情页查看获取；  
- virtual-kubelet运行的可用区：与Kubernetes集群支持的可用区一致，或Kubernetes集群支持的可用区的子集；详情参考地域与可用区说明；    
- 实例规格分类：设置virtual-kubelet创建原生容器Pod时默认选择的实例规格分类，例如：g.n2；更多实例规格类型参考[价格总览][2]；  
3. 使用Kubectl确认virtual-kubelet的状态：  
`   
kubectl get pods -n kube-system
`   
确定virtual-kubelet虚节点的数量与指定的可用区数量一致确状态为Running即说明virtual-kubelet部署成功；  

```nodes  
[root@*** jdcloud-virtual-kubelet]# kubectl get nodes
NAME                         STATUS    ROLES     AGE       VERSION
k8s-node-*******-90****snb   Ready     <none>    10d       v1.8.12-249.9d2635d
k8s-node-*******-90****snb   Ready     <none>    10d       v1.8.12-249.9d2635d
k8s-node-*******-90****snb   Ready     <none>    10d       v1.8.12-249.9d2635d
virtual-kubelet-cn-****-2a   Ready     agent     3d        v1.8.3
virtual-kubelet-cn-****-2b   Ready     agent     3d        v1.8.3
```  

## virtual-kubelet 依赖文件说明   

- nc.toml   
- virtual-kubelet-serviceaccount.yaml    
- virtual-kubelet-deployment.yaml    

1. virtual-kubelet 启动配置文件 nc.toml，文件内容及说明如下：  

```toml  
[System]
   OperatingSystem = "Linux"		#Node的操作系统. 默认为Linux
[Metadata]
  Region = "cn-east-2"		#创建的原生容器Pod所在地域，必须与Kubernetes集群在同一个地域
  AvailableZone = "cn-east-2a" 		#创建的原生容器Pod所在可用区，必须是Kubernetes集群支持的可用区
  ClusterID = "k8s-1m******lv"	 	#Kubernetes集群的ID
  PodSubnetId = "subnet-ds******2v"		#创建的原生容器Pod所在的子网，必须与Kubernetes集群Pod所在的子网一致
  PodSecurityGroups = ["sg-12******o7"] 		#Pod子网绑定的安全组ID
  AccessKey = "4B06***********************01B9" 		#创建Kubernetes集群关联的AccessKey
  SecretKey = "EA94************************18A1"		#创建Kubernetes集群关联的SecretKey
[Resource]
  ContainerDefaultCPU = "125m"		#未设置resource request/limit时容器cpu的默认值；1C=1000m
  ContainerDefaultMEM = "256Mi"		##未设置resource request/limit时容器memory的默认值；1G=1024Mi
  Pods = "50"		# 一个Virtual-kubelet虚节点默认支持的原生容器Pod数量；
  TotalCPU = "72"		#一个Virtual-kubelet虚节点默认支持的CPU总核数；
  TotalMEM = "576Gi"		#一个Virtual-kubelet虚节点默认支持的Memory总容量
  InstanceTypeFamily = "g.n2"		#Virtual-kubelet虚节点创建原生容器Pod时默认选择的实例规格族

```  

2.  virtual-kubelet的部署文件：  

- virtual-kubelet-serviceaccount：创建 virtual-kubelet 对应的 serviceaccount，提供访问K8S APIserver、 操作 Pod 等资源的权限。  
- virtual-kubelet-secret，使用pem格式的X 509证书进行10250 端口访问认证，用于 kubectl logs获取容器日志 以及 Kubectl exec在容器中执行命令。  

```yaml  

---
apiVersion: v1
kind: ServiceAccount
metadata:
  name: virtual-kubelet
  namespace: kube-system
  labels:
    k8s-app: virtual-kubelet
---
apiVersion: rbac.authorization.k8s.io/v1beta1
kind: ClusterRoleBinding
metadata:
  name: virtual-kubelet
  namespace: kube-system
subjects:
- kind: ServiceAccount
  name: virtual-kubelet
  namespace: kube-system
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: cluster-admin
---
apiVersion: v1
kind: Secret
metadata:
  name: virtual-kubelet
  namespace: kube-system
type: Opaque
data:
  cert.pem: xxxxxxxxxxxxxxxxxxxxxxxxxxx==   #cert.pem的内容
  key.pem: xxxxxxxxxxxxxxxxxxxxxxxxxxxx==   #key.pem的内容

```  

3. virtual-kubelet-deployment.yaml 定义Deployment，运行 virtual-kubelet 应用 (Deployment高可用、易维护，也可以直接创建virtual-kubelet的POD)  
- **说明**：在deployment中，replicas必须为1，即指定期望被创建地virtual-kubelet的Pod数量为1  


```yaml  
# virtual-kubelet-deployment.yaml 
apiVersion: extensions/v1beta1
kind: Deployment
metadata:
  name: virtual-kubelet-cn-****-2a		#cn-****-2a为运行脚本时指定的可用区
  namespace: kube-system
  labels:
    k8s-app: kubelet
spec:
  replicas: 1
  selector:
    matchLabels:
      k8s-app: virtual-kubelet-cn-south-1a
  template:
    metadata:
      labels:
        k8s-app: virtual-kubelet-cn-south-1a
    spec:
      hostNetwork: true
      initContainers:
      - name: init-config
        image: virtual-kubelet:1.0 	 #kubernetes集群node节点上的virtual-kubelet镜像
        imagePullPolicy: IfNotPresent
        env:
        - name: AVALIABILITY_ZONE
          value: cn-south-1a
        command: ["init-config.sh"]
        volumeMounts:
        - name: configs
          mountPath: "/etc/virtual-kubelet/config"
          readOnly: false
      containers:
      - name: virtual-kubelet
        image: virtual-kubelet:1.0
        imagePullPolicy: IfNotPresent
        env:
        - name: KUBERNETES_SERVICE_HOST
          value: 10.0.0.4
        - name: KUBERNETES_SERVICE_PORT
          value: "6443"
        - name: KUBELET_PORT
          value: "10251"
        - name: APISERVER_CERT_LOCATION
          value: /etc/virtual-kubelet/cert/cert.pem
        - name: APISERVER_KEY_LOCATION
          value: /etc/virtual-kubelet/cert/key.pem
        - name: DEFAULT_NODE_NAME
          value: virtual-kubelet-cn-south-1a
        - name: VKUBELET_POD_IP
          valueFrom:
            fieldRef:
              fieldPath: status.podIP
        volumeMounts:
        - name: credentials
          mountPath: "/etc/virtual-kubelet/cert"
          readOnly: true
        - name: configs
          mountPath: "/etc/virtual-kubelet/config"
          readOnly: true
        command: ["virtual-kubelet"]
        args: [
          "--provider", "jdcloud",
          "--nodename", "$(DEFAULT_NODE_NAME)",
          "--provider-config", "/etc/virtual-kubelet/config/nc-cn-south-1a.toml",
          "--os", "Linux"
        ]
        livenessProbe:
          tcpSocket:
            port: 10251
          initialDelaySeconds: 20
          periodSeconds: 20
      volumes:
      - name: credentials
        secret:
          secretName: virtual-kubelet
      - name: configs
        hostPath:
          path: /etc/jdcloud-virtual-kubelet/config
          type: DirectoryOrCreate
      serviceAccountName: virtual-kubelet
```  

  [1]: http://kubernetes.oss.cn-north-1.jcloudcs.com/virtual-kubelet/jdcloud-virtual-kubelet.tar.gz
  [2]: https://docs.jdcloud.com/cn/native-container/price-overview