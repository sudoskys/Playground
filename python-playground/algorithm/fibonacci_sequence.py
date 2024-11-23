def can_form_arithmetic_sequence(lst):
    if len(lst) < 3:
        return False
    lst.sort()
    diff = lst[1] - lst[0]
    for i in range(2, len(lst)):
        if lst[i] - lst[i - 1] != diff:
            return False
    return True

user_input = input()
numbers = list(map(int, user_input.split(',')))
result = can_form_arithmetic_sequence(numbers)
print(result)

def fibonacci(n):
    if n <= 0:
        return 0
    elif n == 1 or n == 2:
        return 1
    else:
        return fibonacci(n - 1) + fibonacci(n - 2)

fibonacci_sequence = [fibonacci(i) for i in range(1, 21)]
print(fibonacci_sequence)

def is_prime(n):
    if n <= 1:
        return False
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:
            return False
    return True

sum_of_primes = sum(i for i in range(2, 101) if is_prime(i))
print(sum_of_primes)

def 汉诺塔(n, source, target, auxiliary):
   if n == 1:
       print(f"{source}->{target}")
   else:
       汉诺塔(n - 1, source, auxiliary, target)
       print(f"{source}->{target}")
       汉诺塔(n - 1, auxiliary, target, source)

N = int(input())
汉诺塔(N, 'A', 'C', 'B')