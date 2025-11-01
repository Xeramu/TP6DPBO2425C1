<< Saya, Umarex Shauma Andromeda, dengan NIM 2400598, mengerjakan TP6 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek. Untuk keberkahan-Nya, maka saya tidak akan melakukan kecurangan seperti yang telah dispesifikasi >>

A. Design Program


1. App.java

   Ini class utama buat ngejalanin gamenya. Ngatur window utama sama menghubungkan class logic dan view.
   
2. Logic.java

   Isinya logika buat gamenya kayak gerakan burung, pipa, gravitasi, skor, sama ngecek si burung nabrak pipa apa engga.
   
3. View.java

   View jd UI buat gamenya. Ngegambar background, burung, pipa, skor, sama message game over.
   
4. Player.java

   Ini buat nyimpen data burung kayak posisi ukuran, kecepatan jatohnya burung, sama gambar burung.
   
5. Pipe.java

   Buat nyimpen data setiap pipa. Kayak posisi, ukuran, gambar, sama status udh silewatin apa blm.
   
6. MainMenuFlappyBirdElAngjay

    Buat nampilin menu utama. Isinya tombol [PLAY] sama [EXIT].


B. Penjelasan Alur


   1. Main Menu

      Saat program dijalanin, tampilan yg pertama kali muncul adalah main menu. Isinya button [PLAY] atau [EXIT]. Klo klik [EXIT] ntar windownya bakal ke close. Klo klik [PLAY], ntar gamenya lgsng mulai.
      
   2. Game
      - Abis klik play, ntar game lgsng dimulai. Ada background, skor di tengah atas, sama burung (kita).
      - Burung udh mulai jatuh kebwah krn ada gravity, trus pipa mulai generate dr kanan layar, bergerak ke kiri layar.
      - Player neken tombol spasi biar burungnya loncat ke atas. Pas burungnya ngelewatin pipa, skornya bertambah sebiji.
      - Klo burungnya nyium pipa atau jatuh kebwah, gamenya stop trus muncul message "GAME OVER" sama "Press R to restart" di screen.
      - Player bisa mencet R untuk restrat gamenya.

C. Dokumentasi


1. Main Menu


   <img width="543" height="1006" alt="image" src="https://github.com/user-attachments/assets/dd55280b-1b53-4b99-8158-765d880da42a" />


2. Game

   
   <img width="543" height="1006" alt="image" src="https://github.com/user-attachments/assets/7db476d7-9d1f-4941-8938-e81f6af7d71a" />


3. Game Over

   
   <img width="543" height="1006" alt="image" src="https://github.com/user-attachments/assets/9502935e-216b-4399-a266-2c0af87e95cf" />


4. Video
   
   
https://github.com/user-attachments/assets/f6cf9a6f-e347-4da3-a6e9-85477fe540e7
