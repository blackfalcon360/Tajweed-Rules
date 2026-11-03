# Tajweed Rules – تجويد کے قواعد

An offline Android learning guide for the essential rules of Qur'anic recitation. Built in **Java** with native Android views, following the same project structure and language setup as the supplied PeriodicTable project.

## Features
- English, Urdu and Arabic interface; English is selected by default.
- Dark mode enabled by default, with a light-mode option.
- Search by rule name, category, Arabic title or example.
- Category filters for foundations, Noon Sakinah & Tanween, Meem Sakinah, Ghunnah, Qalqalah, Madd, Lam & Ra, stopping and recitation.
- Tap any rule to open its explanation, Qur'anic example and learning note.
- Fully offline; no permissions, network access or external libraries.
- Android Java source; GitHub Actions workflow builds a debug APK.

## Included learning topics
Makharij al-Huruf, Sifat al-Huruf, Izhar, Idgham with/without Ghunnah, Iqlab, Ikhfa, rules of Meem Sakinah, Ghunnah, Qalqalah, Madd Tabii, Muttasil, Munfasil, Lazim, Lam of Allah, Ra, Waqf & Ibtida, Isti'adhah and Basmalah.

## Build APK with GitHub
1. Extract this ZIP.
2. Upload the contents of the `TajweedRules` folder to a GitHub repository (the `app` folder must be at the repository root).
3. Open the repository's **Actions** tab.
4. Run **Build APK** (or push to `main` / `master`).
5. Open the completed workflow run and download the `TajweedRules-APK` artifact.

## Important learning note
This app is a concise introductory reference, not a substitute for learning recitation directly from a qualified Qur'an teacher. Tajweed details and madd lengths can vary by riwayah and transmission route. Examples are illustrative; verify recitation with a qualified teacher and a trusted Mushaf.

## Project
- Package: `com.blackfalcon.periodictable` (kept unchanged from the supplied project)
- Language: Java 17
- minSdk: 24
- compileSdk: 34
- Default language: English
- Default theme: Dark

By: Black Falcon 🦅
