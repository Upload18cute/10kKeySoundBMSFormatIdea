# 99,999 KeySound BMS File Format Idea
~~I hate my bad english.~~

Well, just an idea.


But it would be nice to use, don't you think?


## File Layout:
```
*---------------------- HEADER FIELD
#PLAYER Single / Double
#GENRE Genre
#TITLE Title
#ARTIST Artist
#BPM 100
#PLAYLEVEL 1
#RANK 3
#TOTAL 100
#STAGEFILE file.bmp

#RANDOM 6
#IF 1
#ARTIST Upload18
#ENDIF
#ENDRANDOM

#WAV00001: key.wav
     └ 00001 ~ 99999

*---------------------- MAIN DATA FIELD
#001.16.008.000.0: 00001
└ bar.line.split.index.type: 00000
    1 ~ 128 ┘     │    │
  0 ~ (split - 1) ┘    │
      0: Short 1: Long ┘

#001.02: 0.75 // i
└ Time signature (Not sure. Because I gave up on maths.):
m = round(i x 4)
n = 4

 m / GCD(m, n)
───────────────
 n / GCD(m, n)
```
![Example](https://github.com/Upload18cute/10kKeySoundBMSFormatIdea/blob/main/Example.png)


## Example.java Output
```
File: ../song.nbms

============================

-=-=- HEADER FIELD -=-=-

Player: 1
Genre: genre
Title: title
Artist: artist
BPM: 130
PlayLevel: 1
Rank: 3
Total: 100
Volwav: 75.0
StageFile: file.bmp

LNType: 1

Wav List:{1=key1.wav, 2=key2.wav, 3=key3.wav, 4=d.wav}

Wav 1: key1.wav
Wav 2: key2.wav
Wav 3: key3.wav
Wav 4: d.wav

Speed 1: 1.11

Scroll 1: 2.22

Stop 1: 3.33

BpmNote 1: 4.44

What is this: {MORETEST=TESTVALUE, TEST=no value}

-=-=- MAIN DATA FIELD -=-=-

-=- Scroll note -=-
Bar: 1
Line: SC
Split: 8
Index: 0
Type: 0
Wav: 00001
WavFile: key1.wav

-=- Stop note -=-
Bar: 1
Line: 09
Split: 8
Index: 0
Type: 0
Wav: 00001
WavFile: key1.wav

-=- BPM note -=-
Bar: 1
Line: 08
Split: 8
Index: 0
Type: 0
Wav: 00001
WavFile: key1.wav

-=- Speed note -=-
Bar: 1
Line: SP
Split: 8
Index: 0
Type: 0
Wav: 00001
WavFile: key1.wav

Bar: 1
Line: 16
Split: 8
Index: 0
Type: 0
Wav: 00001
WavFile: key1.wav

Bar: 1
Line: 16
Split: 8
Index: 3
Type: 0
Wav: 00004
WavFile: d.wav

-=- Time Signature note -=-
Bar: 1
Time Signature: 3.0 / 4

Bar: 1
Line: 16
Split: 8
Index: 1
Type: 0
Wav: 00002
WavFile: key2.wav

Bar: 1
Line: 16
Split: 8
Index: 2
Type: 0
Wav: 00003
WavFile: key3.wav

============================
```


## TODO:
1. I think need more if
