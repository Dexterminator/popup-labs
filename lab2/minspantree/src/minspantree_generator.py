__author__ = 'dexter'

import random


def generate():
    n = 20000
    m = 30000
    print(n, m)
    for i in range(n - 1):
        print(i, i + 1, random.randint(-20000, 20000))
    for i in range(m - n + 1):
        print(random.randint(1, n-1), random.randint(1, n - 1), random.randint(-20000, 20000))


def main():
    for i in range(16):
        generate()
    print(0, 0)


if __name__ == '__main__':
    main()
