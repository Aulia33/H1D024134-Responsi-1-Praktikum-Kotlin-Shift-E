# JelajahRasa - Aplikasi Katalog & Eksplorasi Resep Makanan 🍳📖

Aplikasi Android modern berbasis **Jetpack Compose** dan **Material Design 3 (M3)** yang dikembangkan menggunakan arsitektur **MVVM (Model-View-ViewModel)**. Aplikasi ini memungkinkan pengguna untuk mencari, mengeksplorasi, dan melihat detail resep kuliner dari berbagai negara secara dinamis melalui konsumsi REST API **TheMealDB**.

---

## 👤 Identitas Praktikan
- **Nama Lengkap:** Siti Aulia Febriana
- **NIM:** H1D024134
- **Shift Awal:** Shift B
- **Shift Akhir:** Shift E
- **Link Video Demo/Penjelasan:**
  
---
## 📸 Tampilan Aplikasi (Screenshots)

https://drive.google.com/file/d/1LiQBSGi6ntPf4Zon5-8sW26kMHHbyNLS/view?usp=drivesdk 
<img width="716" height="1600" alt="image" src="https://github.com/user-attachments/assets/b6747658-ae9f-4ec7-93bb-038c1c4de0b3" />
<img width="716" height="1600" alt="image" src="https://github.com/user-attachments/assets/c324bfe4-ef38-4cd5-8c57-ae3d66912196" />
<img width="716" height="1600" alt="image" src="https://github.com/user-attachments/assets/5b32fd51-1528-44f9-9b8a-03a838a1a219" />


---

## ✨ Fitur Utama Aplikasi

1. **Eksplorasi & Pencarian Resep (Home Screen)**
   - Menampilkan daftar resep kuliner dunia menggunakan **LazyColumn** secara optimal.
   - Fitur **Pencarian Real-Time (Search Bar)** berdasarkan kata kunci nama makanan.
   - Filter Kategori & Asal Kuliner (Horizontal Chip Row) interaktif (*Chicken, Beef, Pasta, Seafood, Japanese, Italian, dll.*).
   - Penanganan UI State secara utuh (**Loading State**, **Error State**, **Empty State**, dan **Success State**).

2. **Detail Resep Kuliner (Recipe Detail Screen)**
   - Tampilan *Hero Banner* dengan badge rating ("★ 4.9") dan status "Teruji Dapur".
   - Informasi durasi memasak, tingkat kesulitan, serta jumlah porsi.
   - **Daftar Bahan-Bahan Interaktif**: Dilengkapi dengan **Checkbox** interaktif untuk menandai bahan yang sudah disiapkan, fitur *strikethrough* otomatis, serta tombol **Reset**.
   - **Langkah-Langkah Memasak (Cara Memasak)**: Tampilan kartu berurut (Step 1, 2, 3, dst.) dengan deskripsi langkah yang jelas.
   - Integrasi link ke **Video YouTube Tutorial** dan **Sumber Asli Resep** via Implicit Intent.
   - Floating Sticky Bottom Bar "Siap Masak? / Mulai Memasak".

3. **Bookmark & Resep Favorit (Favorites Tab)**
   - Menandai resep pilihan dengan tombol bookmark.
   - Halaman khusus untuk mengakses resep-resep pilihan pengguna.

4. **Profil Pengembang & Spesifikasi (Profile Tab)**
   - Halaman profil mahasiswa/pengembang beserta rangkuman teknologi yang digunakan.

---

## 🏗️ Arsitektur Aplikasi (MVVM Architecture)

Aplikasi ini menerapkan pola arsitektur **MVVM (Model - View - ViewModel)** sesuai standar Android Jetpack:

```
┌─────────────────────────────────────────────────────────────┐
│                      VIEW (UI Layer)                        │
│   HomeScreen • DetailScreen • FavoriteScreen • Components   │
└──────────────────────────────┬──────────────────────────────┘
                               │ Observes UI State (StateFlow)
                               ▼
┌─────────────────────────────────────────────────────────────┐
│                     VIEWMODEL Layer                         │
│               HomeViewModel • DetailViewModel               │
└──────────────────────────────┬──────────────────────────────┘
                               │ Requests Data / Coroutines
                               ▼
┌─────────────────────────────────────────────────────────────┐
│                    REPOSITORY Layer                         │
│                      MealRepository                         │
└──────────────────────────────┬──────────────────────────────┘
                               │ Fetches DTOs
                               ▼
┌─────────────────────────────────────────────────────────────┐
│                    DATA & NETWORK Layer                     │
│        RetrofitInstance • MealApiService • TheMealDB API    │
└──────────────────────────────┬──────────────────────────────┘
```

1. **Model & Network Layer:**
   - `MealDto`: Data Transfer Object untuk mapping response JSON dari REST API.
   - `Meal`: Domain Data Model yang bersih dan siap dikonsumsi oleh UI.
   - `MealApiService`: Antarmuka Retrofit dengan endpoint TheMealDB.
   - `RetrofitInstance`: Singleton Retrofit client yang dikonfigurasi dengan `OkHttpClient` dan `HttpLoggingInterceptor`.
   - `MealRepository`: Mengisolasi data fetching dari API dan memetakan DTO menjadi Domain Model.

2. **ViewModel Layer:**
   - `HomeViewModel` & `DetailViewModel`: Mengelola state aplikasi menggunakan `StateFlow` (`HomeUiState` & `DetailUiState`) serta memproses logic pencarian, filter, dan checkbox bahan.

3. **View Layer (Jetpack Compose):**
   - Menggunakan Composable functions yang bersifat *reusable* dan *state-driven*. Pengelolaan API **100% diisolasikan dari Composable**.

---

## 🌐 REST API yang Digunakan

Aplikasi mengonsumsi **TheMealDB REST API** (Endpoint Gratis/Tanpa Key):
1. **Search Recipe API:**
   - Endpoint: `https://www.themealdb.com/api/json/v1/1/search.php?s={nama_makanan}`
   - Penggunaan: Mengambil daftar resep berdasarkan query pencarian atau kata kunci default.
2. **Lookup Detail Recipe API:**
   - Endpoint: `https://www.themealdb.com/api/json/v1/1/lookup.php?i={id_recipe}`
   - Penggunaan: Mengambil detail lengkap resep berdasarkan `idMeal`.

---

## 🛠️ Penerapan Fitur Kotlin & Jetpack Compose

- **Kotlin Data Class:** Digunakan pada `MealDto`, `MealResponse`, `Meal`, dan `Ingredient`.
- **Null Safety:** Penanganan `nullable` fields dari API menggunakan operator `?.`, `?:`, `orEmpty()`, dan safe casting.
- **Extension Functions:** `MealDto.toMeal()`, `String.capitalizeWords()`, dan `List<Ingredient>.toIngredientSummary()`.
- **Higher-Order Functions & Lambda:** Implementasi `fold`, `map`, `filter`, `forEachIndexed`, `flatMap`, dan event handler callbacks (`onMealClick`, `onFavoriteToggle`, `onCheckedChange`).
- **State & Recomposition:** Menggunakan `collectAsState()` untuk merekomposisi UI secara otomatis ketika terjadi perubahan data pada `StateFlow`.
- **Lazy Layout:** `LazyColumn` dan `LazyRow` untuk efisiensi render list.

---

## 🚀 Cara Menjalankan Proyek

1. Clone repository ini:
   ```bash
   git clone https://github.com/Aulia33/H1D024134-Responsi-1-Praktikum-Kotlin-Shift-E.git 
   ```
2. Buka proyek di **Android Studio**.
3. Pastikan koneksi internet aktif untuk mendownload gradle dependencies dan fetching data dari REST API.
4. Sync Gradle dan jalankan aplikasi pada Emulator atau Perangkat Android (Min SDK 24 / Android 7.0+).
