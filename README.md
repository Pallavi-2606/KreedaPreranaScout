# Kreeda Prerana Scout

An Android application developed using Kotlin and Jetpack Compose for managing and analyzing student sports performance. The application provides performance tracking, leaderboard management, authentication, and Firebase cloud integration to support efficient sports data management.

---

# 📱 Project Overview

Kreeda Prerana Scout is designed to help institutions and organizations monitor student sports activities, maintain rankings, and improve athlete performance analysis through a modern Android application.

The application focuses on:
- Student performance management
- Sports activity tracking
- Leaderboard generation
- Authentication and secure login
- Cloud database integration using Firebase

---

# 🚀 Features

## ✅ Authentication System
- User Login
- Secure Authentication
- Firebase Authentication Integration

## ✅ Student Performance Tracking
- Add student performance details
- Manage sports activity records
- Store athlete information

## ✅ Leaderboard Module
- Rank students based on performance
- Dynamic leaderboard updates
- Performance comparison

## ✅ Firebase Integration
- Cloud database storage
- Real-time data synchronization
- Backend support

## ✅ Modern UI
- Built with Jetpack Compose
- Responsive Android UI
- User-friendly interface

---

# 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Kotlin | Android Application Development |
| Jetpack Compose | Modern UI Design |
| Firebase Authentication | User Authentication |
| Firebase Realtime Database / Firestore | Cloud Database |
| Android Studio | Development Environment |
| Gradle | Build Automation |

---

# 📂 Project Structure

```bash
KreedaPreranaScout/
│
├── .idea/                         # Android Studio project settings
│
├── app/
│   ├── src/main/
│   │
│   │   ├── java/com/example/kreedaprernascout/
│   │   │
│   │   │   ├── AnalyticsActivity.kt
│   │   │   ├── AppDatabase.kt
│   │   │   ├── DashboardActivity.kt
│   │   │   ├── LeaderboardActivity.kt
│   │   │   ├── LeaderboardAdapter.kt
│   │   │   ├── LeaderboardItem.kt
│   │   │   ├── LoginActivity.kt
│   │   │   ├── MainActivity.kt
│   │   │   ├── Performance.kt
│   │   │   ├── PerformanceDao.kt
│   │   │   ├── RegisterActivity.kt
│   │   │   ├── Student.kt
│   │   │   ├── StudentAdapter.kt
│   │   │   ├── StudentDao.kt
│   │   │   └── TrialActivity.kt
│   │
│   │   ├── res/
│   │   │
│   │   │   ├── drawable/         # Images and drawable resources
│   │   │
│   │   │   ├── layout/
│   │   │   │   ├── activity_analytics.xml
│   │   │   │   ├── activity_dashboard.xml
│   │   │   │   ├── activity_leaderboard.xml
│   │   │   │   ├── activity_login.xml
│   │   │   │   ├── activity_main.xml
│   │   │   │   ├── activity_register.xml
│   │   │   │   ├── activity_trial.xml
│   │   │   │   ├── item_leaderboard.xml
│   │   │   │   └── item_student.xml
│   │   │
│   │   │   ├── mipmap-anydpi-v26/
│   │   │   ├── mipmap-hdpi/
│   │   │   ├── mipmap-mdpi/
│   │   │   ├── mipmap-xhdpi/
│   │   │   ├── mipmap-xxhdpi/
│   │   │   ├── mipmap-xxxhdpi/
│   │   │
│   │   │   ├── values/           # Colors, strings, themes
│   │   │   └── xml/              # XML configuration files
│   │
│   │   └── AndroidManifest.xml
│   │
│   ├── .gitignore
│   ├── build.gradle.kts
│   ├── google-services.json
│   └── proguard-rules.pro
│
├── gradle/
│
├── .gitignore
├── README.md
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
└── settings.gradle.kts
```

---

# 📁 Folder & File Description

| File/Folder | Description |
|---|---|
| `AnalyticsActivity.kt` | Displays analytics and performance insights |
| `AppDatabase.kt` | Room database configuration |
| `DashboardActivity.kt` | Main dashboard screen of the application |
| `LeaderboardActivity.kt` | Displays student rankings |
| `LeaderboardAdapter.kt` | RecyclerView adapter for leaderboard |
| `LeaderboardItem.kt` | Data model for leaderboard items |
| `LoginActivity.kt` | User login functionality |
| `MainActivity.kt` | Entry point of the application |
| `Performance.kt` | Performance data model |
| `PerformanceDao.kt` | Database operations for performance data |
| `RegisterActivity.kt` | User registration screen |
| `Student.kt` | Student data model |
| `StudentAdapter.kt` | Adapter for displaying student data |
| `StudentDao.kt` | Database operations for student records |
| `TrialActivity.kt` | Trial/performance activity management |
| `layout/` | XML layouts for application screens |
| `drawable/` | Image and drawable resources |
| `mipmap-*` | App launcher icons for different resolutions |
| `values/` | Strings, themes, and color resources |
| `xml/` | XML configuration files |
| `google-services.json` | Firebase configuration file |
| `proguard-rules.pro` | Rules for code shrinking and optimization |
| `gradle/` | Gradle wrapper and build management |
| `README.md` | Project documentation |

---

# 🏗️ Architecture Overview

The project follows a modular Android application structure with:
- Activity-based navigation
- Room Database for local storage
- Firebase integration for cloud services
- XML-based UI layouts
- RecyclerView adapters for dynamic lists

---

# 🔄 Application Workflow

```text
User Login/Register
        ↓
Dashboard Screen
        ↓
Performance Management
        ↓
Leaderboard Generation
        ↓
Analytics & Reports
```

---

# 📌 Core Modules

## 🔐 Authentication Module
Handles:
- User Login
- User Registration
- Authentication validation

Files:
- `LoginActivity.kt`
- `RegisterActivity.kt`

---

## 📊 Performance Management Module
Handles:
- Student performance records
- Sports activity tracking
- Database operations

Files:
- `Performance.kt`
- `PerformanceDao.kt`

---

## 🏆 Leaderboard Module
Handles:
- Student ranking
- Score calculation
- Dynamic leaderboard display

Files:
- `LeaderboardActivity.kt`
- `LeaderboardAdapter.kt`
- `LeaderboardItem.kt`

---

## 👨‍🎓 Student Management Module
Handles:
- Student details
- Student records
- Data retrieval and display

Files:
- `Student.kt`
- `StudentDao.kt`
- `StudentAdapter.kt`

---

# ⚡ Build Configuration

The project uses:
- Kotlin DSL Gradle (`build.gradle.kts`)
- Android SDK
- Firebase services
- Room Database dependencies

---

# 🔥 Firebase Integration

Firebase services integrated:
- Firebase Authentication
- Firebase Realtime Database / Firestore
- Firebase Cloud Services

Configuration file:
```bash
google-services.json
```

---

# 🧪 Testing & Optimization

Implemented:
- UI testing
- Database validation
- Performance tracking functionality

Future scope:
- Unit testing
- Integration testing
- Performance optimization

---

# 👩‍💻 Developer

**Pallavi C**

GitHub:
https://github.com/Pallavi-2606
