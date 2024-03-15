#include <iostream>
#include <cstring>
using namespace std;

#define MAXSIZE 100

typedef struct
{
    char ch[MAXSIZE];
    int length;
} SString;

typedef struct
{
    char *ch;
    int length;
} HString;

int StrIndex_BF(SString s, SString t, int pos)
{
    int i = pos, j = 1;
    while (i <= s.length && j <= t.length)
    {
        if (s.ch[i] == t.ch[j])
        {
            ++i;
            ++j;
        }
        else
        {
            i = i - j + 2;
            j = 1;
        }
    }
    if (j > t.length)
    {
        return i - t.length;
    }
    return 0;
}

int StrIndex_BF(HString s, HString t, int pos)
{
    int i = pos, j = 1;
    while (i <= s.length && j <= t.length)
    {
        if (s.ch[i] == t.ch[j])
        {
            ++i;
            ++j;
        }
        else
        {
            i = i - j + 2;
            j = 1;
        }
    }
    if (j > t.length)
    {
        return i - t.length;
    }
    return 0;
}

int main()
{
    SString s, t;
    cin >> s.ch + 1 >> t.ch + 1;
    s.length = strlen(s.ch + 1);
    t.length = strlen(t.ch + 1);
    int pos = StrIndex_BF(s, t, 1);
    if (pos == 0)
    {
        cout << "Location: not found!";
    }
    else
    {
        cout << "Location: " << pos;
    }
    return 0;
}
