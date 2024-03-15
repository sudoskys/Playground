# -*- coding: utf-8 -*-
# @Time    : 10/6/22 1:55 PM
# @FileName: 9_isPalindrome.py
# @Software: PyCharm
# @Github    ：sudoskys

class Solution(object):
    def isPalindrome(self, x):
        """
        :type x: int
        :rtype: bool
        """
        return x > -1 and str(x)[::-1] == str(x)


xs = 1225521
res = Solution().isPalindrome(xs)
print(res)
