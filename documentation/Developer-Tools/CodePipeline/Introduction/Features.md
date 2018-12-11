# 功能
## 实现发布过程自动化
### 端到端的流水线
从代码提交、构建、测试、部署，实现完整的端到端的流水线功能。通过Webhook触发或者事件监听的方式，自动执行流水线。同时，流水线也提供人工把控的能力，通过在执行过程中加入手工审批操作的方式，支持用户手工测试或者手工部署至不同环境。
### 自定义流水线
根据用户在产品不同阶段的不同侧重点，支持灵活配置流水线。同一阶段内的任务可以设置为串行执行或并行执行，满足多种使用场景。