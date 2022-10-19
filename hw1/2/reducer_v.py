#!/usr/bin/env python3

import sys

mj = 0
cj = 0
vj = 0

for line in sys.stdin:
    ck, mk, vk = map(float, line.split())
    vj = (cj * vj + ck * vk) / (cj + ck) + \
         cj * ck * ((mj - mk) / (cj + ck)) ** 2
    mj = (cj * mj + ck * mk) / (cj + ck)
    cj += ck


print(vj)