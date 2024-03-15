#include <stdio.h>
#include <string.h>

#define MAXSIZE 1000

typedef char SString[MAXSIZE + 1];

void GetNext(SString t, int next[])
{
    int i = 1, j = 0;
    next[1] = 0;
    while (i < strlen(t))
    {
        if (j == 0 || t[i - 1] == t[j - 1])
        {
            ++i;
            ++j;
            next[i] = j;
        }
        else
        {
            j = next[j];
        }
    }
}

int StrIndex_KMP(SString s, SString t, int pos)
{
    int i = pos, j = 1, next[MAXSIZE];
    GetNext(t, next);
    while (i <= strlen(s) && j <= strlen(t))
    {
        if (j == 0 || s[i - 1] == t[j - 1])
        {
            ++i;
            ++j;
        }
        else
        {
            j = next[j];
        }
    }
    if (j > strlen(t))
    {
        return i - strlen(t) - 1;
    }
    else
    {
        return 0;
    }
}

int main()
{
    SString s, t;
    fgets(s, MAXSIZE, stdin);
    fgets(t, MAXSIZE, stdin);
    s[strlen(s) - 1] = '\0'; // 将字符串末尾的换行符替换为'\0'
    t[strlen(t) - 1] = '\0';
    int pos = StrIndex_KMP(s, t, 1);
    if (pos)
    {
        printf("Location: %d\n", pos);
    }
    else
    {
        printf("Location: not found!\n");
    }
    return 0;
}
