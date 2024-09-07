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

#WAV00001: key.wav
└00001 ~ 99999

*---------------------- MAIN DATA FIELD
#001.16.008.000.0: 00001
└bar.line.split.index.type: 00000 // i don't think you need this 'WAV'
    1 ~ 128 ┘     │    │
  0 ~ (split - 1) ┘    │
      0: Short 1: Long ┘

#001.02: 0.75
└Time signature: ↓↓↓↓↓↓
```
![Example](https://github.com/Upload18cute/10kKeySoundBMSFormatIdea/blob/main/Example.png)

\[
\text{Time signature} = \frac{\frac{m}{\text{GCD}(m, n)}}{\frac{n}{\text{GCD}(m, n)}}
\]


## TODO:
1. Add #VOLWAV, #BMP, #BGA and set time signature
2. Filter Speed, Scroll, Bpm, Stop, BGA, Layer, Poor
