import math

def binomial_coefficient(n, m):
    if 0 <= m <= n <= 20:
        return math.factorial(n) // (math.factorial(m) * math.factorial(n - m))
    else:
        return "error"


if __name__ == "__main__":
    string=input()
    n, m = map(int, string.split())
    print(binomial_coefficient(n, m))