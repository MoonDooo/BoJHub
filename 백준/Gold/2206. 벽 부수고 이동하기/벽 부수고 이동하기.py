from collections import deque


row, col = map(int, input().split())
isVisited = [[[False] * 2 for _ in range(col)] for _ in range(row)]
road = [list(map(int, list(input()))) for _ in range(row)]

directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

q = deque()
q.append((0, 0, 0, 1))  # x, y, isUsed, moves

while q:
    x, y, isUsed, moves = q.popleft()
    if x == col - 1 and y == row - 1:  # 도착 시
        print(moves)
        exit()

    if isVisited[y][x][isUsed]:
        continue
    isVisited[y][x][isUsed] = True

    for dx, dy in directions:
        nx, ny = x + dx, y + dy
        if nx < 0 or col <= nx or ny < 0 or row <= ny:
            continue
        if road[ny][nx] == 1 and isUsed == 0:
            q.append((nx, ny, 1, moves + 1))
        elif road[ny][nx] == 0 and not isVisited[ny][nx][isUsed]:
            q.append((nx, ny, isUsed, moves + 1))

print(-1)