#!/usr/bin/env python3

import sys

mj = 0
cj = 0

for line in sys.stdin:
    ck, mk = map(float, line.split())
    mj = (cj * mj + ck * mk) / (cj + ck)
    cj += ck

print(mj)