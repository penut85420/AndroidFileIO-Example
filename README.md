# File IO Example

## Introduction

+ 簡易的 Android 檔案 IO 範例程式

## Description

+ 要順利讀寫外部檔案（位於 `/sdcard/` 下的檔案），需要以下條件
  1. SDK 版本小於 30
  2. 無論在 Manifest 內或者 Runtime 時都要取得讀寫權限
  3. 操作 `File` 物件時，仔細檢查是否為 `null`

## Usage

+ 透過 Android Studio 的 Device File Explorer，將 `Config.json` 放置在 `/sdcard/Hello/MyData` 底下

## SDK Version

+ 關於 SDK 版本設定，請參考 `src/main/AndroidManifest.xml`

## Permission Request

+ 在 Manifest 內要加上 Permission 的敘述
+ 在程式碼執行階段向使用者請求權限，可以參考 `src/main/java/com/example/example/Hello.java`

## File Operation

+ 對於檔案的路徑確認、讀寫操作，請參考 `src/main/java/com/example/example/MainActivity.kt`

## Note

+ 系統只會向使用者請求至多兩次權限設定，若是使用者拒絕兩次，就不會再出現權限請求的視窗
+ 開發時可以選擇解除安裝並重新安裝，使用者則必須透過設定開關權限
