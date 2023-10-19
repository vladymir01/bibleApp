package com.example.bibleapp.data.model

data class Bible(
    val newTestament: List<Book> = bookNewTestament,
    val oldTestament: List<Book> = bookOldTestament
)

val bookOldTestament = listOf<Book>(
    Book("GEN", "65eec8e0b60e656b-01", "GEN", "Genesis", "Genesis"),
    Book("EXO", "65eec8e0b60e656b-01", "EXO", "Exodus", "Exodus"),
    Book("LEV", "65eec8e0b60e656b-01", "LEV", "Leviticus", "Leviticus"),
    Book("NUM", "65eec8e0b60e656b-01", "NUM", "Numbers", "Numbers"),
    Book("DEU", "65eec8e0b60e656b-01", "DEU", "Deuteronomy", "Deuteronomy"),
    Book("JOS", "65eec8e0b60e656b-01", "JOS", "Joshua", "Joshua"),
    Book("JDG", "65eec8e0b60e656b-01", "JDG", "Judges", "Judges"),
    Book("RUT", "65eec8e0b60e656b-01", "RUT", "Ruth", "Ruth"),
    Book("1SA", "65eec8e0b60e656b-01", "1SA", "1 Samuel", "1 Samuel"),
    Book("2SA", "65eec8e0b60e656b-01", "2SA", "2 Samuel", "2 Samuel"),
    Book("1KI", "65eec8e0b60e656b-01", "1KI", "1 Kings", "1 Kings"),
    Book("2KI", "65eec8e0b60e656b-01", "2KI", "2 Kings", "2 Kings"),
    Book("1CH", "65eec8e0b60e656b-01", "1CH", "1 Chronicles", "1 Chronicles"),
    Book("2CH", "65eec8e0b60e656b-01", "2CH", "2 Chronicles", "2 Chronicles"),
    Book("EZR", "65eec8e0b60e656b-01", "EZR", "Ezra", "Ezra"),
    Book("NEH", "65eec8e0b60e656b-01", "NEH", "Nehemiah", "Nehemiah"),
    Book("EST", "65eec8e0b60e656b-01", "EST", "Esther", "Esther"),
    Book("JOB", "65eec8e0b60e656b-01", "Job", "Job", "Job"),
    Book("PSA", "65eec8e0b60e656b-01", "PSA", "Psalms", "Psalms"),
    Book("PRO", "65eec8e0b60e656b-01", "PRO", "Proverbs", "Proverbs"),
    Book("ECC", "65eec8e0b60e656b-01", "ECC", "Ecclesiastes", "Ecclesiastes"),
    Book("SNG", "65eec8e0b60e656b-01", "SNG", "Song of Songs", "Song of Songs"),
    Book("ISA", "65eec8e0b60e656b-01", "ISA", "Isaiah", "Isaiah"),
    Book("JER", "65eec8e0b60e656b-01", "JER", "Jeremiah", "Jeremiah"),
    Book("LAM", "65eec8e0b60e656b-01", "LAM", "Lamentations", "Lamentations"),
    Book("EZK", "65eec8e0b60e656b-01", "EZK", "Ezekiel", "Ezekiel"),
    Book("DAN", "65eec8e0b60e656b-01", "DAN", "Daniel", "Daniel"),
    Book("HOS", "65eec8e0b60e656b-01", "HOS", "Hosea", "Hosea"),
    Book("JOL", "65eec8e0b60e656b-01", "JOL", "Joel", "Joel"),
    Book("AMO", "65eec8e0b60e656b-01", "AMO", "Amos", "Amos"),
    Book("OBA", "65eec8e0b60e656b-01", "OBA", "Obadiah", "Obadiah"),
    Book("JON", "65eec8e0b60e656b-01", "JON", "Jonah", "Jonah"),
    Book("MIC", "65eec8e0b60e656b-01", "MIC", "Micah", "Micah"),
    Book("NAM", "65eec8e0b60e656b-01", "NAM", "Nahum", "Nahum"),
    Book("HAB", "65eec8e0b60e656b-01", "HAB", "Habakkuk", "Habakkuk"),
    Book("ZEP", "65eec8e0b60e656b-01", "Zephaniah", "Zephaniah", "Zephaniah"),
    Book("HAG", "65eec8e0b60e656b-01", "HAG", "Haggai", "Haggai"),
    Book("ZEC", "65eec8e0b60e656b-01", "Zechariah", "Zechariah", "Zechariah"),
    Book("MAL", "65eec8e0b60e656b-01", "Malachi", "Malachi", "Malachi")
)

val bookNewTestament = listOf<Book>(
    Book("MAT", "65eec8e0b60e656b-01", "MAT", "Matthew", "Matthew"),
    Book("MRK", "65eec8e0b60e656b-01", "MRK", "Mark", "Mark"),
    Book("LUK", "65eec8e0b60e656b-01", "LUK", "Luke", "Luke"),
    Book("JHN", "65eec8e0b60e656b-01", "JHN", "John", "John"),
    Book("ACT", "65eec8e0b60e656b-01", "ACT", "Acts", "Acts"),
    Book("ROM", "65eec8e0b60e656b-01", "ROM", "Romans", "Romans"),
    Book("1CO", "65eec8e0b60e656b-01", "1CO", "1 Corinthians", "First Corinthians"),
    Book("2CO", "65eec8e0b60e656b-01", "2CO", "2 Corinthians", "Second Corinthians"),
    Book("GAL", "65eec8e0b60e656b-01", "GAL", "Galatians", "Galatians"),
    Book("EPH", "65eec8e0b60e656b-01", "EPH", "Ephesians", "Ephesians"),
    Book("PHP", "65eec8e0b60e656b-01", "PHP", "Philippians", "Philippians"),
    Book("COL", "65eec8e0b60e656b-01", "COL", "Colossians", "Colossians"),
    Book("1TH", "65eec8e0b60e656b-01", "1TH", "1 Thessalonians", "First Thessalonians"),
    Book("2TH", "65eec8e0b60e656b-01", "2TH", "2 Thessalonians", "Second Thessalonians"),
    Book("1TI", "65eec8e0b60e656b-01", "1TI", "1 Timothy", "First Timothy"),
    Book("2TI", "65eec8e0b60e656b-01", "2TI", "2 Timothy", "Second Timothy"),
    Book("TIT", "65eec8e0b60e656b-01", "TIT", "Titus", "Titus"),
    Book("PHM", "65eec8e0b60e656b-01", "PHM", "Philemon", "Philemon"),
    Book("HEB", "65eec8e0b60e656b-01", "HEB", "Hebrews", "Hebrews"),
    Book("JAS", "65eec8e0b60e656b-01", "JAS", "James", "James"),
    Book("1PE", "65eec8e0b60e656b-01", "1PE", "1 Peter", "First Peter"),
    Book("2PE", "65eec8e0b60e656b-01", "2PE", "2 Peter", "Second Peter"),
    Book("1JN", "65eec8e0b60e656b-01", "1JN", "1 John", "First John"),
    Book("2JN", "65eec8e0b60e656b-01", "2JN", "2 John", "Second John"),
    Book("3JN", "65eec8e0b60e656b-01", "3JN", "3 John", "Third John"),
    Book("JUD", "65eec8e0b60e656b-01", "JUD", "Jude", "Jude"),
    Book("REV", "65eec8e0b60e656b-01", "REV", "Revelation", "Revelation")
)