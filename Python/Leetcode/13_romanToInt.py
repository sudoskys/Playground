# -*- coding: utf-8 -*-
# @Time    : 10/6/22 2:08 PM
# @FileName: 13_romanToInt.py
# @Software: PyCharm
# @Github    ：sudoskys
class Solution(object):
    def _map(self, x):
        self.RomanMap = {
            "I": 1,
            "V": 5,
            "X": 10,
            "L": 50,
            "C": 100,
            "D": 500,
            "M": 1000,
        }
        return self.RomanMap[x]

    def romanToInt(self, s):
        """
        :type s: str
        :rtype: int
        """
        # create map
        int_map = list(map(self._map, list(s)))
        sum = 0
        # control
        for key, item in enumerate(int_map):
            _control = +item
            if len(int_map) != key+1:
                if int_map[key] < int_map[key + 1]:
                    _control = -item
            sum += _control
        return sum


sr = "IIID"
res = Solution().romanToInt(sr)
print(res)
