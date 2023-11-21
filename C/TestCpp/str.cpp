/*
实现字符串的模式匹配的简单算法（BF算法）。建议使用字符串的顺序存储完成.
//从主串s的第pos个字符开始查找子串t。若找到，则返回子串t在主串s中第一次出现的位置，否则返回0。
int StrIndex_BF(SString s, SString t, int pos)
或者 int StrIndex_BF(HString s, HString t, int pos)
输入说明: 
第一行输入主串 
第二行输入子串 
输出格式: 
输出Location: #，其中#是子串在主串中的位置编号
输入样例2：
mystring
aring
输出样例2：
Location: not found!
*/

#include <iostream>
#include <cstring>
using namespace std;

const int N = 1010;

struct SString {
    char str[N];
    int length;
};

// Brute force string matching algorithm
int StrIndex_BF(SString s, SString t, int pos)
{
    int i = pos, j = 1;
    while (i <= s.length && j <= t.length) {
        if (s.str[i] == t.str[j]) { // If characters match, move on to the next ones
            ++i, ++j;
        } else { // If characters don't match, move back to the beginning of the current comparison and start over
            i = i - j + 2, j = 1;
        }
    }
    if (j > t.length) return i - t.length; // If all characters in t have been matched, return the starting index of t in s
    else return 0; // Otherwise, t is not found in s
}

int main()
{
    SString s, t;

    // Input strings
    cin >> s.str + 1 >> t.str + 1;

    // Get lengths of strings
    s.length = strlen(s.str + 1);
    t.length = strlen(t.str + 1);

    // Call function to find t in s starting from position 1
    int pos = StrIndex_BF(s, t, 1);

    // Output result
    if (pos) cout << "Location: " << pos << endl;
    else cout << "Location: not found!" << endl;

    return 0;
}
