#include <iostream>
#include <cstring>
using namespace std;


// KMP 获取Next模式的数组，这是一个 O(n) 的算法
// @param t: the string to be found
// @param next: the next array of t
// @return the starting index of t in s, or 0 if t is not found in s
void GetNext(string t, int next[])
{
    // 请注意，我们这里是逐步匹配初始和探测的距离的，这个距离代表匹配成功的长度。

    // 然后 j 是匹配到的最长前缀的长度
    // i 是当前匹配到的位置
    int i = 1, j = 0;
    // next[0] is not used
    next[1] = 0;
    while (i < t.length()) {
        // 如果 j == 0 或者当前字符匹配成功（即 t[i] == t[j]），都令 i++，j++，继续匹配下一个字符
        // j==0，没匹配开始或者前后移动匹配成功了。
        // j 代表匹配了多少个相同的组块了。
        if (j == 0 || t[i] == t[j]) {
            ++i, ++j;
            next[i] = j;
            // 填充 next 数组
        } else {
            j = next[j];
            // 这里是 i 不变，j 变成 next[j]，即让模式串右移一位
        }
    }
}

int KMP(string s, string t,int pos)
{
    // 获取 Next 数组
    int *next= new int[t.size()];
    GetNext(t, next);
    // i 是主串 s 当前匹配到的位置，j 是子串 t 当前匹配到的位置
    int i = pos, j = 0;
    while (i < s.length() && j < t.length()) {
        // 如果 j == 0 或者当前字符匹配成功（即 s[i] == t[j]），都令 i++，j++，继续匹配下一个字符
        if (j == 0 || s[i] == t[j]) {
            ++i, ++j;
        } else {
            j = next[j];
        }
    }
    delete [] next;

    // 如果 j 超出了 t 的长度，说明匹配成功
    if (j > t.length()) return i - t.length();
    else return 0;
}



int main(){
    string s,t;

    // 输入一下
    getline(cin,s);
    getline(cin,t);
    
    // 定位子串的位置
    int pos=KMP(s,t,0);
    if (pos) cout << "Location: " << pos << endl;
    else cout << "Location: not found!" << endl;
    return 0;
}

