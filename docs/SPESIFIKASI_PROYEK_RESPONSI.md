# Spesifikasi Proyek Responsi: Aplikasi Katalog dan Eksplorasi Resep

## 1. Permasalahan
Terdapat banyak jenis makanan dan resep dari berbagai negara, kategori, serta bahan yang dapat digunakan untuk memasak. Pengguna membutuhkan cara yang lebih mudah untuk mencari, melihat, dan mengeksplorasi berbagai resep makanan melalui perangkat mobile.
Dalam proyek ini, mahasiswa diminta untuk mengembangkan aplikasi mobile yang dapat mengambil dan menampilkan informasi resep makanan dari REST API secara dinamis. Aplikasi tidak hanya berfungsi sebagai tampilan data, tetapi juga harus menerapkan konsep-konsep yang telah dipelajari selama perkuliahan Mobile Programming.

---

## 2. Persyaratan Teknis

### A. Bahasa Pemrograman
Menggunakan **Kotlin** dengan memanfaatkan fitur-fitur Kotlin sebaik-baiknya:
1. Data class
2. Null safety
3. Lambda / Higher-Order Functions
4. Extension function (jika diperlukan)

### B. User Interface (UI)
User interface aplikasi wajib menggunakan:
1. **Jetpack Compose**:
   - Composable layout
   - Lazy layout
   - Reusable composable
2. **Material Design 3 (M3)**
3. **Theme dan Typography**

### C. List dan Data
- Menampilkan kumpulan resep menggunakan komponen *lazy layout* (pilih salah satu):
  1. `LazyColumn`
  2. `LazyVerticalGrid`
- Data yang ditampilkan **wajib berasal dari REST API**.
- Minimal informasi yang ditampilkan pada list:
  1. Nama makanan
  2. Gambar makanan
  3. Kategori makanan
  *(Informasi tambahan bersifat opsional)*

### D. State dan Recomposition
Menerapkan *state-driven UI* dan menunjukkan pemahaman terhadap proses *recomposition*. Minimal terdapat:
1. Search functionality (mencari resep berdasarkan nama makanan)
2. Loading state
3. Error state
4. Perubahan UI berdasarkan state

### E. Networking
Wajib menggunakan **TheMealDB API**:
- Dokumentasi: TheMealDB API Documentation
- Tidak membutuhkan API Key untuk endpoint gratis/dasar.
- **Endpoint yang digunakan:**
  - **Search Recipe:** `https://www.themealdb.com/api/json/v1/1/search.php?s={nama_makanan}`
  - **Detail Recipe:** `https://www.themealdb.com/api/json/v1/1/lookup.php?i={id_recipe}`
- **Data minimal yang digunakan:**
  1. Nama makanan
  2. Gambar makanan
  3. Kategori
  4. Asal makanan (Area)
  5. Instruksi memasak
  6. Bahan-bahan yang digunakan (Ingredients)
  7. Takaran bahan (Measures)
  *(Informasi lainnya pada API bersifat opsional dan menjadi nilai tambah)*

### F. Architecture
Wajib menerapkan pola arsitektur **MVVM (Model - View - ViewModel)**:
1. View / Composable
2. ViewModel
3. Repository
4. Retrofit
5. API Service
6. Data Model
> **Catatan:** Pengelolaan data dari API **tidak boleh** dilakukan secara langsung di dalam Composable.

---

## 3. Screens (Minimal 2 Layar)

### 1. Home Screen
Menampilkan:
- Judul aplikasi
- Search bar (pencarian resep)
- Daftar resep (Gambar makanan, Nama makanan, Kategori makanan)

### 2. Recipe Detail Screen
Menampilkan informasi resep yang dipilih:
- Gambar makanan
- Nama makanan
- Kategori
- Asal makanan
- Daftar bahan dan takaran
- Instruksi memasak
*(Informasi tambahan dari API dapat ditampilkan sebagai nilai tambah)*

---

## 4. Submission & Deadline
Pengumpulan proyek terdiri dari:
1. **Github repository** yang berisi kode aplikasi.
2. **README.md** yang berisikan:
   - Screenshot aplikasi
   - Penjelasan fitur
   - Penjelasan architecture (MVVM)
   - Penjelasan API yang digunakan
   - Penjelasan teknis mengenai implementasi aplikasi
3. **Video penjelasan kode**:
   - Berfokus pada penjelasan kode dan implementasi (bukan sekadar demo aplikasi).
4. Pengumpulan link Github dan video pada Google Form yang disediakan.
5. **Deadline Pengumpulan:** 9 Oktober 2026, 17.00 WIB.
