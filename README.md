# My-treasure

GU2 项目：前端 + 后端放在同一个仓库里。

## 目录结构

```
.
├── frontend/    # 前端页面（WebStorm）
│   ├── Login.html
│   ├── Register.html
│   ├── Player.html
│   ├── PlayerProfile.html
│   ├── Coach.html
│   ├── Chairperson.html
│   ├── Membership.html
│   ├── MembershipAssistant.html
│   ├── FixtureAssistant.html
│   ├── NonplayerMember.html
│   ├── DifferentRoles.html
│   └── package.json
└── backend/     # 后端服务（IntelliJ IDEA / Spring Boot + Maven）
    ├── pom.xml
    ├── mvnw / mvnw.cmd
    └── src/
```

## 本地开发

前端：直接用浏览器打开 `frontend/` 下的 HTML，或在该目录起一个静态服务器。

后端：

```bash
cd backend
./mvnw spring-boot:run      # Windows: mvnw.cmd spring-boot:run
```

## 说明

- `.idea/`、`target/`、`node_modules/` 等生成物已通过 `.gitignore` 排除，不纳入版本控制。
- 两个 IDE 分别打开各自的子目录即可，仓库根目录只有一个 `.git`。
