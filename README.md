# Brownian Motion Analysis

A Java application for analyzing Brownian motion through image
processing and particle tracking. The application detects beads in
consecutive image frames, calculates their displacements, and uses the
resulting measurements to estimate the Boltzmann and Avogadro constants.

## Overview

The analysis pipeline consists of the following steps:

1. **Image loading:** Read sequential JPEG images from a specified
    directory.
2. **Image thresholding:** Convert images into binary images using a
    grayscale intensity threshold.
3. **Blob detection:** Identify connected foreground components using
    depth-first search (DFS).
4. **Bead filtering:** Filter detected components according to their
    pixel mass.
5. **Particle tracking:** Compare consecutive frames and calculate
    nearest-neighbor displacements.
6. **Physical analysis:** Estimate the diffusion coefficient, Boltzmann
    constant, and Avogadro constant.
7. **Data export:** Save displacement measurements to a text file.

## Project Structure

``` text
.
├── Main.java
├── detection/
│   ├── BlobDetector.java
│   └── BeadFinder.java
├── image/
│   └── ImageProcessor.java
├── io/
│   └── DisplacementIO.java
├── lib/
│   └── Picture.java
├── model/
│   └── Blob.java
├── physics/
│   ├── Constants.java
│   └── PhysicalCalc.java
└── tracking/
    └── BeadTracker.java
```

## Package Responsibilities

  Package       Responsibility
  ------------- --------------------------------------------------
  `image`       Image thresholding and processing
  `detection`   Connected-component detection and bead filtering
  `model`       Representation of detected blobs
  `tracking`    Particle displacement calculations
  `physics`     Physical constants and mathematical calculations
  `io`          Reading and writing displacement measurements
  `lib`         Image representation and pixel manipulation

## Implementation Details

### Image Processing

Images are converted into binary representations using a grayscale
intensity threshold.

The grayscale intensity is calculated using:

$$
I = 0.299R + 0.587G + 0.114B
$$

Pixels with intensity greater than or equal to the threshold are
classified as foreground (white); the remaining pixels are classified as
background (black).

### Blob Detection

Connected components are identified using a four-directional depth-first
search algorithm.

Each detected component is represented by a `Blob` object, which
maintains:

- The total number of pixels (mass).
- The sum of horizontal pixel coordinates.
- The sum of vertical pixel coordinates.
- The calculated centroid.

The centroid is calculated as:

$$
x_c = \frac{\sum_{i=1}^{N} x_i}{N}
$$

$$
y_c = \frac{\sum_{i=1}^{N} y_i}{N}
$$

where $N$ is the number of pixels belonging to the component.

### Particle Tracking

For every pair of consecutive frames, detected beads are compared using
their centroid coordinates.

The Euclidean distance between two blobs is:

$$
d = \sqrt{(x_1-x_2)^2+(y_1-y_2)^2}
$$

The tracker finds the nearest bead in the previous frame for each bead
in the current frame. This is a nearest-neighbor approach; it does not
guarantee one-to-one assignments between frames.

### Physical Calculations

For Brownian motion in $d$ spatial dimensions, the mean squared
displacement is related to the diffusion coefficient by:

$$
\left\langle r^2(t) \right\rangle = 2dDt
$$

For two-dimensional motion, the diffusion coefficient can be estimated
from $N$ independent displacement measurements over a common time
interval $\Delta t$:

$$
D = \frac{1}{4N\Delta t}
\sum_{i=1}^{N}
\left[(\Delta x_i)^2+(\Delta y_i)^2\right]
$$

The pixel displacements must be converted to meters before calculating
$D$. The time interval between frames must also be accounted for. Use
the dimensionality and time interval appropriate to the experiment.

The Boltzmann constant is estimated using the Stokes-Einstein equation:

$$
k_B = \frac{6\pi\eta rD}{T}
$$

where:

- $\eta$ is the dynamic viscosity of the fluid (Pa·s).
- $r$ is the bead radius (m).
- $D$ is the diffusion coefficient (m²/s).
- $T$ is the absolute temperature (K).

The Avogadro constant is then calculated using:

$$
N_A = \frac{R}{k_B}
$$

where $R$ is the universal gas constant.

## Configuration

The analysis parameters are defined in `Main.java`:

  ------------------------------------------------------------------------
  Parameter                            Current value Description
  --------------------- ---------------------------- ---------------------
  `TAU`                                     `180.94` Grayscale intensity
                                                     threshold

  `MIN_PIXELS`                                  `25` Minimum
                                                     pixel-distance
                                                     threshold used by the
                                                     tracker

`MIN_BLOB_MASS`                               `25` Minimum number of
                                                     pixels required for a
                                                     detected bead
  ------------------------------------------------------------------------

Physical constants are defined in `physics/Constants.java`:

  Constant                                         Value
  ------------------------ -----------------------------
  Pixel-to-meter ratio            $0.175 \times 10^{-6}$
  Room temperature                               $297$ K
  Dynamic viscosity          $9.135 \times 10^{-4}$ Pa·s
  Bead radius                     $0.5 \times 10^{-6}$ m
  Universal gas constant             $8.31446$ J/(mol·K)

Adjust these values to match the experimental setup. In particular,
ensure that the pixel-to-meter ratio, frame interval, temperature,
viscosity, and bead radius correspond to the actual experiment.

## Requirements

- Java 17 or later.
- JPEG images containing the experimental observations.
- A directory containing the sequential image frames.

The project uses Java standard libraries and the included `Picture`
class. No external dependencies or build tools are required, assuming
the `Picture` implementation has no additional dependencies.

## Compilation

From the project root directory:

``` bash
javac Main.java \
    image/*.java \
    detection/*.java \
    io/*.java \
    lib/*.java \
    model/*.java \
    physics/*.java \
    tracking/*.java
```

## Execution

Run the application by providing the directory containing the
experimental images:

``` bash
java Main path/to/images
```

For example:

``` bash
java Main ./experiment
```

The application processes JPEG files in lexicographical filename order.

## Output

The application writes the calculated displacement measurements to:

**`displacements.txt`**

The file contains one displacement measurement per line. The terminal
displays the calculated physical quantities, including the diffusion
coefficient (if reported by the application), Boltzmann constant, and
Avogadro constant.

## Limitations

- Particle tracking uses nearest-neighbor matching rather than global
    or one-to-one assignment. A bead in one frame may therefore be
    matched to the same bead as another detection.
- The detection algorithm uses four-directional connectivity.
- Image segmentation relies on a global intensity threshold.
- The current entry point considers JPEG files.
- Physical estimates depend on correct calibration, frame timing, and
    experimental parameters.
