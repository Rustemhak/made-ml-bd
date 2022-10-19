#!/usr/bin/env python3

import sys

PRICE_ID = 7 

sk = 0
ssk = 0
ck = 0

infile = sys.stdin
next(infile)
for line in infile:
    features = line.rsplit(",", PRICE_ID)
    if len(features) > PRICE_ID:
        price = features[1]
        if price is not None:
            price = int(price)
            sk += price
            ssk += price * price
            ck += 1

mk = sk / ck
vk = ssk / ck - mk * mk

print(ck, mk, vk)