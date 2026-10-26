<div dir="rtl">

# 📤 رفع المشروع على GitHub (بدون Token)

## ⚡ الطريقة الأسهل: استخدام SSH

### الخطوة 1: إنشاء Repository على GitHub

1. اذهب إلى https://github.com/new
2. ملء البيانات:
   - **Repository name**: `RostoPhotoPro`
   - **Description**: `محرر الصور الاحترافي المجاني للأندرويد`
   - **Public** ✓
   - **Uncheck** "Initialize with README"
3. اضغط **Create repository**

### الخطوة 2: إعداد SSH (مرة واحدة فقط)

```bash
# إنشاء مفتاح SSH
ssh-keygen -t ed25519 -C "your-email@gmail.com"

# اضغط Enter 3 مرات (بدون كلمة مرور)
# أو اكتب كلمة مرور إذا أردت

# نسخ المفتاح العام
cat ~/.ssh/id_ed25519.pub
```

**على Windows:**
```bash
# استخدم Git Bash
type %USERPROFILE%\.ssh\id_ed25519.pub
```

### الخطوة 3: إضافة المفتاح إلى GitHub

1. اذهب إلى https://github.com/settings/keys
2. اضغط **New SSH key**
3. اختر **Authentication Key**
4. الصق المفتاح الذي نسخته
5. اضغط **Add SSH key**

### الخطوة 4: رفع المشروع

```bash
# انتقل إلى مجلد المشروع
cd /path/to/RostoPhotoPro

# إذا لم يكن Git مهيأ بعد
git init

# إضافة جميع الملفات
git add .

# عمل Commit
git commit -m "Initial commit: ROSTO PHOTO PRO - Professional Photo Editor"

# إضافة الـ remote (استبدل USERNAME بـ GitHub username)
git remote add origin git@github.com:USERNAME/RostoPhotoPro.git

# تغيير اسم الفرع إلى main
git branch -M main

# الرفع!
git push -u origin main
```

---

## ✅ تم! 🎉

المشروع الآن على GitHub!

---

## 📝 كيفية استخدام Git بعد ذلك

### لتحديث المشروع:
```bash
# قم بالتعديل على الملفات
vim app/src/main/kotlin/.../MyFile.kt

# إضافة التغييرات
git add .

# عمل commit
git commit -m "feat: add new feature"

# الرفع
git push
```

### إنشاء فرع جديد:
```bash
git checkout -b feature/my-feature
# اعمل التعديلات
git add .
git commit -m "feat: implement my feature"
git push -u origin feature/my-feature
```

---

## 🆘 استكشاف الأخطاء

### ❌ "Host key verification failed"
```bash
# اختبر الاتصال
ssh -T git@github.com

# اقبل البصمة
# اكتب "yes"
```

### ❌ "Permission denied (publickey)"
```bash
# تحقق من المفتاح
ssh -T git@github.com

# إذا لم ينجح، أعد إنشاء المفتاح
ssh-keygen -t ed25519 -C "your-email@gmail.com"

# أضفه إلى GitHub Settings
```

### ❌ "fatal: not a git repository"
```bash
cd /path/to/RostoPhotoPro
git init
```

---

## 🔐 نصائح الأمان

✅ **SSH أفضل من HTTPS**
- لا تحتاج لكتابة كلمة مرور
- أكثر أماناً
- تلقائي

❌ **لا تستخدم Token في الكود**
- لا تضع GitHub tokens في build.gradle
- لا تضعها في الملفات

---

## 🎯 اختبار الاتصال

```bash
# تأكد من اتصالك بـ GitHub
ssh -T git@github.com

# إذا نجح، ستظهر:
# Hi USERNAME! You've successfully authenticated...
```

---

## 📚 مراجع سريعة

### إضافة ملفات:
```bash
git add .                    # كل الملفات
git add file.kt             # ملف واحد
git add *.kt                # جميع ملفات Kotlin
```

### عرض الحالة:
```bash
git status                   # ماذا تغيّر
git log                      # السجل
git diff                     # التغييرات
```

### التراجع:
```bash
git restore file.kt          # إلغاء التغييرات
git reset                    # إلغاء staging
git revert HEAD~1            # التراجع عن commit
```

---

**🚀 الآن يمكنك البدء في تطوير المشروع!**

</div>
