# master
deny push to branch master

# dev
allow push to branch dev

分支保护有下面5个选项：
#### 1. Require pull request reviews before merging

#### 2. Require branches to be up to date before merging
##### 2.1 Codacy/PR Quality ReviewRequired
##### 2.2 Page Build
##### 2.3 github/pages
 
#### 3.Require signed commits

#### 4.Require linear history

#### 5.nclude administrators

勾选5和1，或者勾选5和2.1可以限制任何人直接提交代码到master
