# 99,999 KeySound BMS File Format Idea


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

#WAV00001: key.wav
└00001 ~ 99999

*---------------------- MAIN DATA FIELD
#001.16.008.000.0: WAV00001
└bar.line.split.index.type: WAV00000
    1 ~ 128 ┘     │    │
  0 ~ (split - 1) ┘    │
      0: Short 1: Long ┘
```
![Example](https://github.com/Upload18cute/10kKeySoundBMSFormatIdea/blob/main/Example.png)




## TODO:
1. Add #VOLWAV, #BMP, #BGA and set time signature
2. Filter Speed, Scroll, Bpm, Stop, BGA, Layer, Poor
