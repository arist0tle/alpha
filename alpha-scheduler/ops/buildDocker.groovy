dir('xxl-job-admin') {
    runDockerBuild(
            'appName': 'xxl-job',
            'dockerRegistry': 'registry.cn-shenzhen.aliyuncs.com',
            'dockerImage': 'xiaomayz/xxl-job'
    )
}
