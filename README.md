# My-treasure

个人项目仓库，目前存放三个练习项目，每个项目都是「前端 + 后端」：

| 项目 | 前端目录 | 后端目录 | 技术栈 |
| --- | --- | --- | --- |
| GU2 俱乐部系统 | `frontend/` | `backend/` | 原生 HTML + Spring Boot + JPA |
| example 示例项目 | `example-web/` | `example-springboot/` | HTML/Bootstrap + Spring Boot + MyBatis-Plus |
| MusicOnline 黑胶交易平台 | `music-web/` | `music-springboot/` | 原生 HTML + Spring Boot 3 + MyBatis-Plus |

## 目录结构

```
.
├── frontend/                 # GU2 前端页面（WebStorm）
├── backend/                  # GU2 后端（IDEA / Spring Boot + Maven）
├── example-web/              # example 前端（WebStorm，含 css/js/images 静态资源）
├── example-springboot/       # example 后端（IDEA / Spring Boot + Maven）
├── music-web/                # MusicOnline 前端（WebStorm）
│   ├── index.html
│   ├── Login.html / Register.html / Search.html / vinyl-detail.html
│   ├── admin/  user/  retailer/
│   └── css/  js/
└── music-springboot/         # MusicOnline 后端（IDEA / Spring Boot 3 + MyBatis-Plus）
    ├── pom.xml
    ├── mvnw / mvnw.cmd
    └── src/
```

## 本地开发

### GU2 项目

前端：浏览器直接打开 `frontend/` 下的 HTML。

后端（端口 8089）：

```bash
cd backend
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```

### example 项目

前端：浏览器直接打开 `example-web/index.html`。

后端（端口 8003）：

```bash
cd example-springboot
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```

### MusicOnline 项目

前端：在 `music-web/` 目录起静态服务器（不要用 file:// 打开，接口请求会被 CORS 拦）：

```bash
cd music-web
python -m http.server 5500    # 或任意静态服务器
```

后端（端口 8080，接口前缀 `/api`）：

```bash
cd music-springboot
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```

## 数据库配置

三个后端的数据库连接都走环境变量，不写死在仓库里：

1. 复制配置模板为真实配置
   - GU2：`backend/src/main/resources/application.properties.example` → `application.properties`
   - example：`example-springboot/src/main/resources/application.yml.example` → `application.yml`
   - MusicOnline：`music-springboot/src/main/resources/application.yml.example` → `application.yml`
2. 设置环境变量（或直接在本地配置文件里填密码）

```powershell
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = "你的密码"
```

## 说明

- `.idea/`、`target/`、`node_modules/` 等生成物通过 `.gitignore` 排除，不纳入版本控制。
- 真实数据库配置文件（`application.properties` / `application.yml`）含密码，**不入库**。
- 各 IDE 直接打开对应子目录即可，仓库根目录只有一个 `.git`。
