# JelajahRasa
**Aplikasi Katalog dan Eksplorasi Resep Makanan Internasional**

---

## 👤 Identitas Praktikan
- **Nama Lengkap:** Siti Aulia Febriana
- **NIM:** H1D024134
- **Shift Awal:** Shift B
- **Shift Akhir:** Shift E
- **Link Video Demo/Penjelasan:** 

---

## 📱 Deskripsi Aplikasi
**JelajahRasa** adalah aplikasi mobile berbasis Android yang memfasilitasi pengguna untuk mencari, mengeksplorasi, dan melihat detail resep kuliner dari berbagai negara secara dinamis. Makanan dan resep dari berbagai belahan dunia memiliki keanekaragaman bahan dan cara memasak. JelajahRasa menyelesaikan masalah sulitnya menemukan panduan memasak yang terstruktur dengan menghadirkan katalog resep dinamis yang terintegrasi langsung dengan REST API **TheMealDB**.

Target pengguna aplikasi ini adalah siapa saja yang ingin mengeksplorasi resep makanan internasional, mulai dari pemula hingga pencinta kuliner yang membutuhkan panduan bahan serta langkah memasak secara interaktif.

---

## 🛠️ Penjelasan Teknis

### 1. Spesifikasi & Tech Stack
- **Bahasa Pemrograman:** Kotlin 2.2.10
- **UI Framework:** Jetpack Compose (Material Design 3)
- **Min SDK:** 24 (Android 7.0) | **Target SDK:** 37
- **Pola Arsitektur:** MVVM (Model-View-ViewModel)
- **Library Utama:**
  - **Navigation Compose:** Pengaturan rute dan navigasi antar layar (`Screen.Home`, `Screen.Detail`, `Screen.Favorites`, `Screen.Profile`).
  - **ViewModel & StateFlow:** Pengelolaan state UI yang reaktif dan *state-driven* (`HomeUiState` & `DetailUiState`).
  - **Retrofit & Gson Converter:** Klien HTTP untuk pengonsumsian REST API TheMealDB dan pemetaan data JSON.
  - **OkHttp Logging Interceptor:** Pengawasan dan logging respon jaringan HTTP.
  - **Coil Compose:** *Image loading* dan *caching* gambar resep secara asinkron.
  - **Kotlin Coroutines & Flow:** Pemrosesan tugas latar belakang (*asynchronous processing*) dan penanganan stream data.

### 2. Fitur Utama
- **Eksplorasi & Pencarian Resep (Home Screen):**
  Menggunakan `LazyColumn` untuk menampilkan daftar resep terpopuler secara efisien. Dilengkapi dengan pencarian real-time via REST API `search.php?s=` berdasarkan kata kunci nama makanan, filter kategori/asal kuliner (*Horizontal Chip Row*), serta penanganan UI state lengkap (*LoadingState*, *ErrorState* dengan tombol retry, *EmptyState*, dan *SuccessState*).
  
- **Detail Resep & Mode Memasak Interaktif (Detail Screen):**
  Menampilkan informasi lengkap resep (Gambar, Nama, Kategori, Asal Negara, Durasi, Tingkat Kesulitan, dan Porsi). Dilengkapi dengan **Daftar Bahan-Bahan Interaktif** menggunakan `Checkbox` beserta tombol *Reset*, instruksi memasak langkah demi langkah, serta fitur unggulan **Mode Memasak Interaktif (Modal Bottom Sheet)** yang membimbing pengguna memasak langkah demi langkah (*Step-by-Step*) dilengkapi dengan indikator progres dan dialog perayaan selesai memasak. Juga menyediakan link langsung ke Video Tutorial YouTube & Sumber Asli Resep.

- **Favorit & Profil Pengembang:**
  Fitur penandaan resep pilihan (*Bookmark*) yang disimpan dalam state aplikasi untuk diakses di halaman Favorit, serta halaman Profil Pengembang beserta rincian informasi teknis aplikasi.

### 3. Struktur Direktori Proyek
```
app/src/main/java/com/example/responsi/
├── data/
│   ├── model/       # Data Models & DTO (Meal, Ingredient, MealDto, MealResponse)
│   ├── remote/      # RetrofitInstance, MealApiService (TheMealDB API)
│   └── repository/  # MealRepository (Penyedia data terisolasi dari UI)
├── ui/
│   ├── components/  # Custom Composable UI reusable (TopHeaderBar, BottomNavBar, SearchBar, MealCard, FilterChipRow, StateViews)
│   ├── detail/      # DetailScreen, DetailViewModel, DetailUiState
│   ├── favorite/    # FavoriteScreen
│   ├── home/        # HomeScreen, HomeViewModel, HomeUiState
│   ├── navigation/  # AppNavigation, Screen
│   ├── profile/     # ProfileScreen
│   └── theme/       # Color, Type, Theme Material 3 (Terracotta Theme)
├── util/            # Extension functions (Extensions.kt)
└── MainActivity.kt  # Root Activity dengan setup Edge-to-Edge
```

---

## 📸 Tangkapan Layar (Screenshots)
*(Tangkapan layar akan ditambahkan oleh praktikan)*

---

## 🚀 Cara Menjalankan Proyek

### Prasyarat:
1. **Android Studio** (Koala / Ladybug / versi terbaru disarankan).
2. **JDK 17** atau lebih baru.
3. Perangkat fisik Android dengan USB Debugging aktif atau Emulator (Min SDK 24 / Android 7.0+).

### Langkah Menjalankan:
1. **Clone repository ini:**
   ```bash
   git clone <URL_REPOSITORY>
   ```
2. Buka folder proyek di **Android Studio**.
3. Tunggu proses **Gradle Sync** selesai secara otomatis.
4. Pastikan koneksi internet aktif untuk proses fetching data dari REST API.
5. Pilih target perangkat/emulator, lalu klik tombol **Run** (`Shift + F10`).
