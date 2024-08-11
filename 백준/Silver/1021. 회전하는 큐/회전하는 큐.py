from collections import deque

N, M = map(int, input().split())
numbers = list(map(int, input().split()))

q = deque(range(1, N+1))

result = 0

for i in numbers:
    idx = q.index(i)
    if len(q)/2<idx:
        rotate = len(q) - idx
        result += rotate
        q.rotate(rotate)
    else:
        rotate = idx
        result += rotate
        q.rotate(-rotate)
    q.popleft()
print(result)