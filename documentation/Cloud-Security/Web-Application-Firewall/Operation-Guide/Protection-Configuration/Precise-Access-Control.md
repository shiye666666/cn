## **精准访问控制**

  精准访问控制支持自定义访问规则，根据客户端IP、请求URL、以及常见的请求头字段过滤访问请求。

对常见的http字段进行条件组合，支持业务场景的定制化防护。

### **功能描述**

  精准访问控制允许您设置访问控制规则，对常见的HTTP字段（如IP、URL、Host、参数等）进行条件组合，用来筛选访问请求，并对命中条件的请求设置放行、阻断、或告警操作。精确访问控制支持业务场景定制化的防护策略，可用于盗链防护、网站管理后台保护等。

  精准访问控制规则由匹配条件与匹配动作构成。在创建规则时，您通过设置匹配字段、逻辑符和相应的匹配内容定义匹配条件，并针对符合匹配条件规则的访问请求定义相应的动作。

### **匹配条件**

匹配条件包含匹配字段、逻辑符、匹配内容。

**说明：** 每一条精准访问控制规则中最多允许设置三个匹配条件组合，且各个条件间是“与”的逻辑关系，即访问请求必须同时满足所有匹配条件才算命中该规则，并执行相应的匹配动作。

### **匹配动作**

精准访问控制规则支持以下匹配动作：

- - **阻断**：阻断命中匹配条件的访问请求。
  - **放行**：放行命中匹配条件的访问请求。
  - **告警**：放行命中匹配条件的访问请求，并针对该请求进行告警。

**说明：** 选择**放行**、**告警**匹配动作后，您可以进一步设置该请求是否需要继续经过其它WAF防护功能检测过滤，如Web应用攻击防护、CC应用攻击防护等。

### **规则匹配顺序**

如果您设置了多条规则，则多条规则间有先后匹配顺序，即访问请求将根据您设定的精准访问控制规则顺序依次进行匹配，顺序较前的精准访问控制规则优先匹配。

您可以通过规则排序功能对所有精准访问控制规则进行排序，以获得最优的防护效果。

### **操作步骤**

参照以下操作步骤，为已防护的域名配置精准访问控制规则：

**说明：** 执行以下操作前，请确保已将网站接入WAF进行防护。具体操作请参考CNAME接入指南
1. 登录[京东云Web应用防火墙控制台](https://cloudwaf-console.jdcloud.com)。

2. 前往**网站配置**页面。

3. 选择要操作的域名，单击其操作列下的**防护配置**。

4. 在**精准访问控制**下，开启防护，并单击**前去配置**。![img](https://github.com/jdcloudcom/cn/blob/edit/image/waf-img/%E7%B2%BE%E5%87%86%E8%AE%BF%E9%97%AE%E6%8E%A7%E5%88%B6-1.png)

5. 单击**新增规则**，设置规则的匹配条件和相应的匹配动作，完成后单击**确定**。![img](https://github.com/jdcloudcom/cn/blob/edit/image/waf-img/%E7%B2%BE%E5%87%86%E8%AE%BF%E9%97%AE%E6%8E%A7%E5%88%B6-2.png)

6. 成功创建规则后，您可以选择执行以下操作：![img](https://github.com/jdcloudcom/cn/blob/edit/image/waf-img/%E7%B2%BE%E5%87%86%E8%AE%BF%E9%97%AE%E6%8E%A7%E5%88%B6-3.png)

7. - **编辑**规则内容或**删除**规则。
   - 如果有多条规则，单击**规则排序**，并操作**上移**、**下移**、**置顶**、**置底**调整规则的匹配顺序。

**说明：** 越靠上的规则越优先匹配。

**配置示例**

精准访问控制规则支持多种配置方法，您可以结合自身业务特点定义相应的规则。通过设置精准访问控制规则也可以实现特定的Web漏洞防护。

以下罗列了一些常用的精确访问控制配置示例，供您参考。

- **配置IP黑白名单**

- - 通过设置以下精准访问控制规则，阻断来自1.1.1.1的所有访问请求。

![img](https://github.com/jdcloudcom/cn/blob/edit/image/waf-img/%E7%B2%BE%E5%87%86%E8%AE%BF%E9%97%AE%E6%8E%A7%E5%88%B6-4.png)

- - 通过设置以下精准访问控制规则，放行来自2.2.2.2的所有访问请求。

![img](https://github.com/jdcloudcom/cn/blob/edit/image/waf-img/%E7%B2%BE%E5%87%86%E8%AE%BF%E9%97%AE%E6%8E%A7%E5%88%B6-5.png)

**说明：** 应用此白名单配置规则时，请不要勾选**继续执行Web应用攻击防护**和**继续执行CC应用攻击防护**等选项，不然访问请求仍可能被WAF的其它防护功能拦截。

- **封禁特定的URL**

如果您遇到有大量IP在刷某个特定且不存在的URL，您可以通过配置以下精准访问控制规则直接阻断所有该类请求，降低源站服务器的资源消耗。

![img](https://github.com/jdcloudcom/cn/blob/edit/image/waf-img/%E7%B2%BE%E5%87%86%E8%AE%BF%E9%97%AE%E6%8E%A7%E5%88%B6-6.png)

- **防盗链**

通过配置Referer匹配字段的访问控制规则，您可以阻断特定网站的盗链。例如，您发现abc.blog.sina.com大量盗用本站的图片，您可以配置以下精准访问控制规则阻断相关访问请求。

![img](https://github.com/jdcloudcom/cn/blob/edit/image/waf-img/%E7%B2%BE%E5%87%86%E8%AE%BF%E9%97%AE%E6%8E%A7%E5%88%B6-7.png)

**支持的匹配字段**

下表列出了精确访问控制支持的匹配字段及其描述。

 

| **匹配字段** | **字段描述**                                             | **适用逻辑符**                                               |
| ------------ | -------------------------------------------------------- | ------------------------------------------------------------ |
| IP           | 访问请求的来源IP。                                       | - 属于    - 不属于                                           |
| URL          | 访问请求的URL地址。                                      | - 包含    - 不包含    - 等于    - 不等于                     |
| Host         |                                                          | - 包含    - 不包含    - 等于    - 不等于                     |
| Post-Body    | 访问请求的响应内容信息。                                 | - 包含    - 不包含    - 等于    - 不等于                     |
| Referer      | 访问请求的来源网址，即该访问请求是从哪个页面跳转产生的。 | - 包含    - 不包含    - 等于    - 不等于    - 长度小于    - 长度等于    - 长度大于    - 不存在 |



 
