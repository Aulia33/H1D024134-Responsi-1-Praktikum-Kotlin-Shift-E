# Materi Dasar Praktikum Pemrograman Mobile (Pertemuan 1 - 4)

Dokumen ini memuat rangkuman materi acuan praktikum dari Pertemuan 1 hingga Pertemuan 4 yang menjadi fondasi dalam membangun proyek aplikasi mobile berbasis Kotlin dan Jetpack Compose.

---

## Pertemuan 1: Inisialisasi Project Android & Implementasi Function Sederhana

### 1. Paradigma UI: Imperative (XML) vs Declarative (Jetpack Compose)
- **Imperative (Tradisional):** Tata letak dibuat di XML terpisah, dikontrol di Kotlin melalui `findViewById` atau binding, manipulasi komponen dilakukan secara manual langkah demi langkah.
- **Declarative (Modern):** Antarmuka dan logika menyatu menggunakan Kotlin murni dengan Jetpack Compose. Tampilan dideskripsikan berdasarkan kondisi (*state*) data saat ini. Ketika state berubah, sistem otomatis melakukan pembaruan (*recomposition*).

### 2. Keunggulan Kotlin
- **Ringkas (Concise):** Mengurangi boilerplate code (otomatisasi getter/setter via `data class`).
- **Aman (Safe):** Fitur *Null Safety* terintegrasi mencegah `NullPointerException` (tanda `?` untuk tipe yang nullable).
- **Ekspresif (Expressive):** Mendukung OOP dan Functional Programming, lambdas, higher-order functions, serta coroutines.
- **Modern & Adaptif:** Interoperabilitas 100% dengan Java, mendukung Kotlin Multiplatform.

### 3. Komponen Dasar Compose
- **`@Composable`**: Anotasi penanda bahwa fungsi tersebut bertugas menggambar UI ke layar. Menurut konvensi penamaan menggunakan *PascalCase*.
- **Komponen Layout:**
  - `Column`: Menumpuk elemen secara vertikal dari atas ke bawah.
  - `Row`: Menata elemen secara horizontal sejajar dari kiri ke kanan.
  - `Box`: Menyusun elemen bertumpuk dari belakang ke depan (layering).
  - `Spacer`: Elemen transparan pengatur jarak antar komponen (`Modifier.height(...)` / `Modifier.width(...)`).
- **Modifier:** Pengatur tata letak, ukuran, padding, background, klik, maupun bentuk komponen:
  - `.fillMaxSize()`, `.fillMaxWidth()`
  - `.padding(...)` (catatan: urutan `.padding` dan `.background` menentukan apakah padding ikut terwarnai).
  - `.clip(...)` (contoh: `CircleShape`, `RoundedCornerShape`).
  - `.weight(Float)`: Membagi ruang kosong secara proporsional di dalam `Row` atau `Column`.

---

## Pertemuan 2: Material Design 3 (M3): Components & Forms

### 1. Sistem Tema (Material You / M3)
- **`Color.kt`**: Definisi palet warna heksadesimal ARGB (misal `Color(0xFF3AA34B)`).
- **`Theme.kt`**: Pengaturan terpusat untuk memetakan warna baku ke hierarki sistem (`ColorScheme`), tipografi (`Typography`), dan bentuk (`Shapes`).
  - Mendukung `LightColorScheme` dan `DarkColorScheme`.
  - Properti `dynamicColor = false` untuk mempertahankan identitas warna aplikasi agar tidak ditimpa wallpaper perangkat.
- **`Type.kt`**: Konfigurasi tipografi standar (`headlineMedium`, `titleLarge`, `bodyMedium`, `labelLarge`) menggunakan skala `sp` dan `FontFamily`.

### 2. Scaffold & TopAppBar
- **`Scaffold`**: Struktur kerangka tata letak standar Material Design yang menyediakan slot untuk `topBar`, `bottomBar`, `floatingActionButton`, dan `snackbarHost`.
- **`TopAppBar`**: Komponen header aplikasi di bagian atas layar untuk menampilkan judul dan tombol navigasi/aksi.

### 3. Komponen Form & Input
- **`OutlinedTextField`**: Komponen input teks bergaris batas tepi, mendukung `leadingIcon`, `trailingIcon`, `isError`, dan `supportingText`.
- **`Button`**: Tombol interaksi untuk aksi pengiriman data atau trigger aksi.
- **`Snackbar` & Coroutine Scope**:
  - `SnackbarHostState` mengelola antrean pesan pop-up ringan non-intrusif.
  - Ditampilkan secara asinkron menggunakan coroutine: `rememberCoroutineScope().launch { snackbarHostState.showSnackbar("Pesan") }`.

### 4. Navigasi Antar Layar Dasar
- Menggunakan pustaka `androidx.navigation:navigation-compose`.
- Komponen utama:
  - `rememberNavController()`: Pengendali riwayat back stack dan navigasi.
  - `NavHost(navController, startDestination)`: Kontainer rute halaman.
  - `composable("route_name") { ScreenContent(...) }`.

---

## Pertemuan 3: Dynamic Lists with Lazy Layouts

### 1. Konsep Lazy Layouts
- **Masalah Layout Standar (`Column`/`Row`):** Memuat seluruh item sekaligus ke memori, tidak efisien jika jumlah item ratusan atau ribuan.
- **Solusi Lazy (`LazyColumn`, `LazyRow`, `LazyVerticalGrid`):** Hanya merender item yang sedang tampak di layar. Item yang keluar layar didaur ulang (*recycled*), sehingga performa tetap lancar dan hemat memori.

### 2. Data Class & Dummy Data
- **`data class`**: Struktur data immutable (`val`) dengan null-safety (`String?`, `Int?`).
- **Singleton `object`**: Menggunakan kata kunci `object` untuk deklarasi singleton (seperti `DummyData`) tanpa perlu membuat instance manual.

### 3. Komponen Card & List Item
- Menggunakan `Card` dengan `CardElevation` dan `CardColors` untuk membungkus setiap item produk/konten.
- Memberikan interaksi klik pada item via `.clickable { onClick() }` atau parameter `onClick`.
- Menampilkan feedback visual menggunakan `Toast.makeText(...)`.

### 4. Pratinjau Desain (`@Preview`)
- Memanfaatkan `@Preview(showBackground = true)` untuk melihat visualisasi komponen langsung di Android Studio tanpa perlu menjalankan emulator.
- Dapat dibuat multi-preview untuk menguji tampilan mode terang (*Light Mode*) dan gelap (*Dark Mode*).

---

## Pertemuan 4: Recomposition, UI Lifecycle & State Management Lanjutan

### 1. State, Recomposition & UDF
- **State:** Data yang menentukan apa yang dirender di layar.
- **`mutableStateOf`:** Membungkus nilai agar menjadi reaktif terhadap perubahan dan memicu *recomposition*.
- **`remember` vs `rememberSaveable`:**
  - `remember`: Menjaga state tetap ada selama recomposition biasa.
  - `rememberSaveable`: Menjaga state tetap bertahan meskipun terjadi *configuration change* (seperti rotasi layar portrait/landscape).
- **Property Delegation (`by`):** Mengizinkan pembacaan/penulisan langsung variabel tanpa memanggil `.value`.
- **Unidirectional Data Flow (UDF):** Data (State) mengalir ke bawah (*downward*) dari parent ke child, aksi pengguna (Events) mengalir ke atas (*upward*) melalui fungsi Lambda.

### 2. State Hoisting
- Memisahkan komponen menjadi dua jenis:
  - **Stateful Composable:** Mengelola state, memegang lifecycle/data, dan meneruskan data ke child.
  - **Stateless Composable:** Murni menerima data lewat parameter dan mengirimkan event aksi via lambda `() -> Unit` atau `(Type) -> Unit`.
- Membuat UI mudah diuji (*testable*), modular, dan dapat dipakai ulang (*reusable*).

### 3. Proses Asinkronus & Loading State
- **`LaunchedEffect(key)`:** Blok composable untuk menjalankan kode suspend/coroutine saat key berubah atau pertama kali diluncurkan.
- Digunakan untuk simulasi penundaan waktu / fetch data dari jaringan (`delay(1000)`).
- Menampilkan indikator loading (`CircularProgressIndicator`) secara kondisional berdasarkan nilai boolean `isLoading`.

### 4. Navigasi dengan Argument
- Mengirimkan parameter rute dinamis seperti `"detail/{productId}"`.
- Mengonfigurasi `arguments = listOf(navArgument("productId") { type = NavType.IntType })`.
- Membaca argumen melalui `backStackEntry.arguments?.getInt("productId")`.
