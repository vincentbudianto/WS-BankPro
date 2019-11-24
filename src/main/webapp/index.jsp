<%--
  Created by IntelliJ IDEA.
  User: VINCENT
  Date: 10/29/2019
  Time: 6:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Web Service - Bank Pro</title>
  </head>
  <body>
    <h1 align="center">
      <b>
        <br>
        # Tugas Besar 2 IF3110 Pengembangan Aplikasi Berbasis Web
        <br>
      </b>
    </h1>

    <h2 align="center">
      <b>
        <br>
        Web Service Bank Pro
        <br>
        <br>
      </b>
    </h2>
    Web service Bank diimplementasikan di atas ​java servlet menggunakan JAX-WS dengan ​protokol SOAP​.
    <br>
    ​Web service ini digunakan oleh Aplikasi Bank Pro dan Aplikasi Engima.
    <br>
    <br>
    Layanan yang disediakan oleh ​web service ​ ini adalah:
    <br>
    1. Validasi nomor rekening. Jika nomor rekening terdaftar di basis data, maka nomor tersebut valid.
    <br>
    <br>
    2. Memberikan data rekening seorang nasabah. Data pengguna meliputi nama pengguna, nomor kartu, saldo terakhir, dan riwayat transaksi (debit dan kredit).
    <br>
    <br>
    3. Melakukan transaksi transfer dengan input nomor rekening pengirim, nomor rekening/akun virtual penerima, dan jumlah uang yang ditransfer. Layanan mengembalikan status transfer (berhasil/gagal).
    <br>
    Transfer dikatakan berhasil jika:
    <br>
    - Nomor rekening atau akun virtual tujuan valid
    <br>
    - Saldo rekening mencukupi untuk transaksi Jika transfer berhasil, akan dicatat transaksi debit pada rekening pengirim dan transaksi kredit pada rekening penerima.
    <br>
    <br>
    4. Membuat akun virtual untuk suatu nomor rekening. Layanan mengembalikan nomor unik akun virtual tersebut.
    <br>
    <br>
    5. Mengecek ada atau tidak sebuah transaksi kredit dalam suatu rentang waktu. Input yang diterima adalah nomor rekening atau akun virtual tujuan, jumlah nominal yang diharapkan, dan ​jangka waktu (dalam menit) rentang waktu (start datetime, end datetime)​.

    <p align="center">
      <b>
        <br>
        <font size="6">
          About
        </font>
      </b>
    </p>

    <p align="center">
      <b>
        IF3110-Pengembangan Aplikasi Berbasis Web - 2019
        <br>
        Teknik Informatika 2017
        <br>
        <br>
        13517137 - Vincent Budianto
      </b>
    </p>
  </body>
</html>
