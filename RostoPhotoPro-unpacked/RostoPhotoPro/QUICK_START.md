<div dir="rtl">

# ⚡ البدء السريع (5 دقائق)

## الخطوة 1️⃣: التثبيت (1 دقيقة)

```bash
# تحميل المشروع
git clone https://github.com/USERNAME/RostoPhotoPro.git
cd RostoPhotoPro
```

## الخطوة 2️⃣: فتح في Android Studio (2 دقيقة)

1. افتح Android Studio
2. اختر **File → Open → RostoPhotoPro**
3. انتظر Gradle Sync ينتهي (قد يأخذ 2-3 دقائق في المرة الأولى)

## الخطوة 3️⃣: التشغيل (2 دقيقة)

### من Android Studio:
1. اضغط على ▶️ **Run** أو Shift+F10
2. اختر المحاكي أو جهازك
3. انتظر التطبيق يشتغل

### من سطر الأوامر:
```bash
./gradlew installDebug
adb shell am start -n com.rosto.photopro/.MainActivity
```

## ✅ تم!

التطبيق يجب أن يكون يعمل الآن! 🎉

---

## 🔨 أوامر مهمة

```bash
# بناء التطبيق
./gradlew build

# تشغيل Tests
./gradlew test

# تنظيف البناء
./gradlew clean

# بناء Release APK
./gradlew assembleRelease
```

## 📱 تجربة الميزات

### من الشاشة الرئيسية:
1. اضغط **"فتح صورة"** واختر صورة من جهازك
2. اضغط **"محرر"** لتعديل الصورة
3. اختر **"السطوع"** وجرّب تحريك الشريط
4. اضغط **"حفظ"** للحفظ

---

## 🆘 مشاكل شائعة

### ❌ "Gradle sync failed"
```bash
./gradlew clean
# ثم جرّب مرة أخرى في Android Studio
```

### ❌ "Android SDK not found"
- افتح Android Studio
- Settings → Appearance & Behavior → System Settings → Android SDK
- ثبّت SDK 34

### ❌ "App won't start"
- جرّب: Build → Clean Project
- ثم: Run → Run 'app'

---

## 📚 التعليم الإضافي

- `README.md` - توثيق شامل
- `DEVELOPMENT.md` - دليل التطوير المتقدم
- `SETUP_GITHUB.md` - كيفية الرفع على GitHub

---

**الآن انطلق وابدأ في التطوير! 🚀**

</div>
