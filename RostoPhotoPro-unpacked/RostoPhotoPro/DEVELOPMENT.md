<div dir="rtl">

# 🛠️ دليل التطوير

## البيئة المطلوبة

### 1. التثبيت الأساسي
- **Java Development Kit (JDK)**: الإصدار 17 أو أحدث
- **Android SDK**: API 34
- **Gradle**: يُثبت تلقائياً عند فتح المشروع

### 2. Android Studio
1. حمّل من [developer.android.com](https://developer.android.com/studio)
2. ثبت باتباع التعليمات
3. افتح المشروع من خلال: File → Open → اختر مجلد RostoPhotoPro

### 3. المحاكي أو جهاز حقيقي
```bash
# قائمة الأجهزة المتصلة
adb devices

# تثبيت APK على جهاز محدد
adb -s DEVICE_ID install app/build/outputs/apk/debug/app-debug.apk
```

## البناء

### البناء من سطر الأوامر

#### البناء العادي
```bash
cd RostoPhotoPro
./gradlew build
```

#### APK التطوير
```bash
./gradlew assembleDebug
# سيكون في: app/build/outputs/apk/debug/app-debug.apk
```

#### APK الإصدار النهائي
```bash
./gradlew assembleRelease
# سيكون في: app/build/outputs/apk/release/app-release.apk
```

#### اختبارات الوحدة
```bash
./gradlew test
```

### البناء من Android Studio
1. اضغط على **Build** في القائمة العلوية
2. اختر:
   - **Build → Make Project** - بناء المشروع
   - **Build → Build Bundle(s) / APK(s) → Build APK(s)** - بناء APK
   - **Run** - بناء وتشغيل على المحاكي

## الهيكل الكودي

### Presentation Layer (العرض)
```
presentation/
├── screens/          # الشاشات الرئيسية
│   ├── HomeScreen.kt
│   ├── EditorScreen.kt
│   └── ProjectsScreen.kt
├── viewmodel/        # ViewModels
│   └── ProjectViewModel.kt
└── components/       # مكونات قابلة لإعادة الاستخدام
```

### Domain Layer (الأعمال)
```
domain/
├── ImageProcessor.kt  # معالجة الصور
└── models/            # نماذج البيانات
```

### Data Layer (البيانات)
```
data/
├── local/
│   └── ProjectDatabase.kt  # Room Database
└── repository/
    └── ProjectRepository.kt  # المستودع
```

### UI Layer (الواجهة)
```
ui/
├── theme/
│   ├── Color.kt
│   ├── Theme.kt
│   └── Type.kt
└── components/       # مكونات Compose
```

## إضافة ميزة جديدة

### 1. إضافة نموذج بيانات
```kotlin
// في domain/models/MyModel.kt
data class MyModel(
    val id: Int,
    val name: String
)
```

### 2. إضافة Repository
```kotlin
// في data/repository/MyRepository.kt
class MyRepository(private val dao: MyDao) {
    suspend fun getData() = dao.getAll()
}
```

### 3. إضافة ViewModel
```kotlin
// في presentation/viewmodel/MyViewModel.kt
class MyViewModel(private val repository: MyRepository) : ViewModel() {
    val data = MutableStateFlow<List<MyModel>>(emptyList())
    
    init {
        loadData()
    }
    
    private fun loadData() {
        viewModelScope.launch {
            data.value = repository.getData()
        }
    }
}
```

### 4. إضافة Screen
```kotlin
// في presentation/screens/MyScreen.kt
@Composable
fun MyScreen(navController: NavController) {
    // واجهة المستخدم
}
```

### 5. التعديل في Navigation
```kotlin
// في MainActivity.kt
NavHost(navController = navController, startDestination = "home") {
    composable("myScreen") {
        MyScreen(navController = navController)
    }
}
```

## إضافة مكتبة جديدة

1. **افتح `app/build.gradle.kts`**
2. **أضف المكتبة في `dependencies`:**
```kotlin
implementation("com.example:library:1.0.0")
```
3. **اضغط على "Sync Now"**

## الاختبار

### اختبارات الوحدة
```kotlin
// في app/src/test/kotlin
import org.junit.Test
import org.junit.Assert.*

class ImageProcessorTest {
    @Test
    fun testAdjustBrightness() {
        val bitmap = createTestBitmap()
        val result = ImageProcessor.adjustBrightness(bitmap, 50f)
        assertNotNull(result)
    }
}
```

### تشغيل الاختبارات
```bash
./gradlew test
```

## تحسينات الأداء

### 1. استخدام lazy في Compose
```kotlin
val list by lazy {
    mutableStateOf(listOf(...))
}
```

### 2. استخدام produceState
```kotlin
val state by produceState<MyState>(initialValue = Loading) {
    delay(1000)
    value = MyState.Success
}
```

### 3. معالجة الصور الكبيرة
```kotlin
// استخدم BitmapRegionDecoder لصور كبيرة
val decoder = BitmapRegionDecoder.newInstance(inputStream, false)
val region = decoder.decodeRegion(rect, options)
```

## التصحيح (Debugging)

### Logcat
```kotlin
Log.d("TAG", "Debug message")
Log.e("TAG", "Error message")
```

### Breakpoints
1. اضغط على رقم السطر لإضافة breakpoint
2. شغّل Debugger
3. استخدم Step Over/Into

### Profiler
1. **Run → Profiler**
2. تابع:
   - CPU usage
   - Memory allocation
   - Network activity

## الأداة الخاصة بـ Lint

```bash
# فحص الأخطاء المحتملة
./gradlew lint

# التقرير يكون في:
# app/build/reports/lint-results.html
```

## توقيع APK الإصدار (Release)

```bash
# إنشاء keystore
keytool -genkey -v -keystore release.jks -keyalg RSA -keysize 2048 -validity 10000

# توقيع APK
jarsigner -verbose -sigalg SHA256withRSA -digestalg SHA-256 \
  -keystore release.jks app/build/outputs/apk/release/app-release-unsigned.apk

# تحسين حجم APK
zipalign -v 4 app-release-unsigned.apk app-release.apk
```

## رفع على Play Store (المستقبل)

1. إنشاء حساب Developer
2. إنشاء تطبيق جديد
3. ملء المعلومات الأساسية
4. رفع APK أو AAB موقع
5. ملء متطلبات المتجر

## الموارد والمراجع

- [Android Developers](https://developer.android.com)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Kotlin Docs](https://kotlinlang.org/docs/)
- [Material 3](https://m3.material.io/)

## نصائح التطوير

1. **استخدم Preview في Compose:**
```kotlin
@Preview
@Composable
fun MyScreenPreview() {
    MyScreen(rememberNavController())
}
```

2. **استخدم Data Classes للنماذج**

3. **اسم الفرع حسب الميزة:**
```bash
git checkout -b feature/image-filters
git checkout -b fix/crash-on-large-images
git checkout -b docs/update-readme
```

4. **اكتب رسائل commit واضحة:**
```
feat: Add brightness adjustment tool
fix: Fix app crash on low memory devices
docs: Update README with installation guide
```

</div>
