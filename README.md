# 99,999 KeySound BMS File Format Idea
~~I hate my bad english~~


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
└ Time signature:
m = round(i x 4)
n = 4

 m / GCD(m, n)
───────────────
 n / GCD(m, n)
```
![Example](https://github.com/Upload18cute/10kKeySoundBMSFormatIdea/blob/main/Example.png)




## TODO:
1. I have no idea
