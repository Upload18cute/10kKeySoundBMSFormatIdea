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

## 박자 계산

주어진 `i` 값을 바탕으로 박자를 계산하는 방법은 다음과 같습니다:

1. **계산식**:
   - **분자 (m)**: 
     \[
     m = \text{round}(i \times 4)
     \]
     (소수점을 반올림하여 정수로 변환)

   - **분모 (n)**:
     \[
     n = 4
     \]

2. **최종 박자**:
   - 최대 공약수 (GCD)를 계산하여 약분합니다.
   - **약분**:
     \[
     \text{박자} = \frac{\frac{m}{\text{GCD}(m, n)}}{\frac{n}{\text{GCD}(m, n)}}
     \]

   여기서 \(\text{GCD}(m, n)\)은 분자 \(m\)과 분모 \(n\)의 최대 공약수입니다.

### 예시

- **i = 1.75**
  - **계산**:
    - \( m = \text{round}(1.75 \times 4) = 7 \)
    - \( n = 4 \)
  - **최종 박자**:
    \[
    \frac{7}{4}
    \]


## TODO:
1. Add #VOLWAV, #BMP, #BGA and set time signature
2. Filter Speed, Scroll, Bpm, Stop, BGA, Layer, Poor
