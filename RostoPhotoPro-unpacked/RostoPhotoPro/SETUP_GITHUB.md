<div dir="rtl">

# 📤 تعليمات رفع المشروع على GitHub

## الخطوة 1: إنشاء Repository جديد على GitHub

1. اذهب إلى [GitHub.com](https://github.com)
2. سجل الدخول أو أنشئ حساب جديد
3. اضغط على **Create a new repository**
4. ملء البيانات:
   - **Repository name**: `RostoPhotoPro`
   - **Description**: `محرر الصور الاحترافي المجاني للأندرويد`
   - **Public** (أم Private - حسب اختيارك)
   - **Initialize with README** - اتركها غير مختارة (لأننا لدينا README بالفعل)

5. اضغط **Create repository**

## الخطوة 2: إعداد المشروع محلياً

### إذا كان المشروع موجود على جهازك:

```bash
cd /path/to/RostoPhotoPro

# تهيئة Git إذا لم تكن مهيأة
git init

# إضافة جميع الملفات
git add .

# عمل Commit أول
git commit -m "Initial commit: ROSTO PHOTO PRO - Professional Photo Editor"

# إضافة الـ remote (استبدل USERNAME و REPO بـ معلوماتك)
git remote add origin https://github.com/USERNAME/RostoPhotoPro.git

# تغيير اسم الفرع الرئيسي إلى main
git branch -M main

# رفع المشروع
git push -u origin main
```

## الخطوة 3: بدون استخدام TOKEN (الطريقة الأسهل)

### استخدام SSH (موصى به):

```bash
# إنشاء SSH Key (إذا لم تكن قد فعلت هذا من قبل)
ssh-keygen -t ed25519 -C "your-email@example.com"

# نسخ المفتاح العام
cat ~/.ssh/id_ed25519.pub

# إضافة المفتاح إلى GitHub:
# 1. اذهب إلى GitHub Settings → SSH and GPG keys
# 2. اضغط "New SSH key"
# 3. الصق المفتاح

# الآن استخدم SSH في الـ remote:
git remote remove origin
git remote add origin git@github.com:USERNAME/RostoPhotoPro.git
git push -u origin main
```

### استخدام HTTPS (بدون Token):

```bash
# استخدم URL الـ HTTPS مباشرة
git remote add origin https://github.com/USERNAME/RostoPhotoPro.git
git push -u origin main

# إذا طلب كلمة المرور:
# في الواقع GitHub تطلب Personal Access Token
# يمكنك استخدام Git Credential Manager بدلاً من ذلك
```

## الخطوة 4: استخدام Git Credential Manager (الأفضل)

### على Windows:
```bash
# تحميل Git Credential Manager
# تجد في: https://github.com/git-ecosystem/git-credential-manager/releases

# بعد التثبيت:
git config --global credential.helper manager-core
```

### على macOS:
```bash
brew install git-credential-manager

git config --global credential.helper manager-core
```

### على Linux:
```bash
# اتبع التعليمات في:
# https://github.com/git-ecosystem/git-credential-manager/blob/main/docs/install.md

git config --global credential.helper manager-core
```

## الخطوة 5: رفع المشروع

بعد إعداد Git:

```bash
# تأكد أنك في مجلد المشروع
cd RostoPhotoPro

# إضافة جميع الملفات
git add .

# عمل commit
git commit -m "Initial commit: ROSTO PHOTO PRO photo editor"

# الرفع الأول
git push -u origin main

# للرفع اللاحق
git push
```

## الخطوة 6: التحديثات المستقبلية

```bash
# تعديل ملف
vim app/src/main/kotlin/com/rosto/photopro/MainActivity.kt

# إضافة التغييرات
git add .

# عمل commit
git commit -m "Add new feature: XYZ"

# الرفع
git push
```

## ✅ تم!

المشروع الآن على GitHub! يمكنك الآن:

- 🔗 مشاركة الرابط مع الآخرين
- 📊 عرض الإحصائيات والـ contributions
- 🔄 إدارة الـ branches والـ pull requests
- 🐛 استخدام Issues لتتبع المشاكل
- 🚀 استخدام GitHub Actions للبناء التلقائي

## 📚 أوامر Git المهمة

```bash
# عرض الحالة
git status

# عرض السجل
git log

# عرض الـ branches
git branch -a

# إنشاء فرع جديد
git checkout -b feature/new-feature

# الرجوع للـ main
git checkout main

# دمج فرع مع main
git merge feature/new-feature

# حذف فرع محلي
git branch -d feature/new-feature

# حذف فرع على GitHub
git push origin --delete feature/new-feature
```

## 🆘 استكشاف الأخطاء

### خطأ: "Permission denied (publickey)"
```bash
# تحقق من الـ SSH key
ssh -T git@github.com

# أعد إنشاء المفتاح
ssh-keygen -t ed25519 -C "your-email@example.com"
```

### خطأ: "fatal: 'origin' does not appear to be a 'git' repository"
```bash
# تحقق من الـ remote
git remote -v

# أضف الـ remote
git remote add origin git@github.com:USERNAME/RostoPhotoPro.git
```

### خطأ: "fatal: The current branch main has no upstream branch"
```bash
# استخدم -u عند الرفع الأول
git push -u origin main
```

## 💡 نصائح إضافية

1. **استخدم SSH** - أسهل وأكثر أماناً من HTTPS
2. **اكتب رسائل commit واضحة** - تساعد على فهم التاريخ
3. **استخدم .gitignore** - تجنب رفع ملفات غير ضرورية
4. **اعمل branching** - للميزات الجديدة والإصلاحات

</div>
