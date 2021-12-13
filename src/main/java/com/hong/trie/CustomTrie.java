package com.hong.trie;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Trie树,前缀匹配实现，忽略大小写
 *
 * @author hong
 */
@Data
public class CustomTrie {
    private TrieNode top;

    @Data
    class TrieNode {
        private boolean isEnd;
        //存储字符，基于ascii表的顺序
        private TrieNode[] trieNodes = new TrieNode[26];
    }

    /**
     * 插入一个字符串
     *
     * @param str 要插入的字符串
     */
    public void insert(String str) {
        if (top == null) {
            top = new TrieNode();
        }
        char[] chars = str.toCharArray();
        TrieNode item = top;
        for (char c : chars) {
            if (item.trieNodes[c - 'a'] == null) {
                item.trieNodes[c - 'a'] = new TrieNode();
            }
            item = item.trieNodes[c - 'a'];
        }
        item.isEnd = true;
    }

    /**
     * 删除trie树的某个字符串
     *
     * @param str 要删除的字符串
     * @return 返回值，1成功，-1失败
     */
    public int delete(String str) {
        if (top == null) {
            return -1;
        }
        TrieNode item = top;
        if (_deleteWord(item, str, 0) == -1) {
            return -1;
        }
        return 1;
    }

    /**
     * 迭代删除str字符串
     *
     * @param item  树的当前节点
     * @param str   要删除的字符串
     * @param count 节点索引
     * @return 返回值，1成功，0无需删除，-1删除失败（表示str不存在于trie树上）
     */
    private int _deleteWord(TrieNode item, String str, int count) {
        if (count == str.length()) {
            if (item.isEnd) {
                item.isEnd = false;
            } else {
                return -1;
            }
        } else {
            TrieNode trieNode = item.trieNodes[str.charAt(count) - 'a'];
            if (trieNode == null) {
                return -1;
            }
            int result = _deleteWord(trieNode, str, count + 1);
            if (result == 1) {
                item.trieNodes[str.charAt(count) - 'a'] = null;
            } else {
                return result;
            }
        }
        if (item.isEnd) {
            return 0;
        }
        for (TrieNode trieNode : item.trieNodes) {
            if (trieNode != null) {
                return 0;
            }
        }
        return 1;
    }

    /**
     * 查找字符串str是否存在于trie树上
     *
     * @param str 要查找的字符串
     * @return 返回值
     */
    public boolean get(String str) {
        if (str == null || top == null || "".equals(str)) {
            return false;
        }
        char[] chars = str.toCharArray();
        TrieNode item = top;
        for (char aChar : chars) {
            item = item.trieNodes[aChar - 'a'];
            if (item == null) {
                return false;
            }
        }
        return item.isEnd;
    }

    /**
     * 匹配前缀pre
     *
     * @param pre 前缀
     * @return 返回所有匹配结果
     */
    public List<String> matchPre(String pre) {
        List<String> results = new ArrayList<>();
        if (pre == null || top == null || "".equals(pre)) {
            return results;
        }
        char[] chars = pre.toCharArray();
        TrieNode item = top;
        for (char aChar : chars) {
            item = item.trieNodes[aChar - 'a'];
            if (item == null) {
                return results;
            }
        }
        //查询item下所有的字符串并加入到result
        if (item.isEnd) {
            results.add(pre);
        }
        _matchWord(item, results, new StringBuilder(pre));
        return results;
    }

    /**
     * 前缀匹配成功后，迭代获取所有之后的字符集
     *
     * @param item    当前节点
     * @param results 结果集
     * @param pre     前缀
     */
    private void _matchWord(TrieNode item, List<String> results, StringBuilder pre) {
        for (int i = 0; i < item.trieNodes.length; i++) {
            if (item.trieNodes[i] != null) {
                StringBuilder tempStr = new StringBuilder(pre);
                tempStr.append((char)('a' + i));
                if (item.trieNodes[i].isEnd) {
                    results.add(tempStr.toString());
                }
                _matchWord(item.trieNodes[i], results, tempStr);
            }
        }
    }

    @Override
    public String toString() {
        List<String> results = new ArrayList<>();
        if (top == null) {
            return "[]";
        }
        _matchWord(top, results, new StringBuilder());
        return results.toString();
    }


    public static void main(String[] args) {
        CustomTrie customTrie = new CustomTrie();
        System.out.println("-------插入数据-------");
        customTrie.insert("abc");
        customTrie.insert("abcd");
        customTrie.insert("bcef");
        customTrie.insert("bceee");
        customTrie.insert("bcefg");
        customTrie.insert("bcefgh");
        System.out.println(customTrie);
        String str1 = "bcefg";
        System.out.println("-------存在查询-------str=" + str1);
        System.out.println(customTrie.get(str1));
        String str2 = "ab";
        System.out.println("-------存在查询-------str=" + str2);
        System.out.println(customTrie.get(str2));
        String str3 = "abce";
        System.out.println("-------存在查询-------str=" + str3);
        System.out.println(customTrie.get(str3));
        String pre1 = "abc";
        System.out.println("-------前缀匹配-------pre=" + pre1);
        System.out.println(customTrie.matchPre(pre1));
        String pre2 = "bcef";
        System.out.println("-------前缀匹配-------pre=" + pre2);
        System.out.println(customTrie.matchPre(pre2));
        String delStr1 = "ab";
        System.out.println("-------删除测试-------str=" + delStr1);
        System.out.println(customTrie.delete(delStr1));
        System.out.println(customTrie);
        String delStr2 = "bceeee";
        System.out.println("-------删除测试-------str=" + delStr2);
        System.out.println(customTrie.delete(delStr2));
        System.out.println(customTrie);
        String delStr3 = "bceee";
        System.out.println("-------删除测试-------str=" + delStr3);
        System.out.println(customTrie.delete(delStr3));
        System.out.println(customTrie);
        String delStr4 = "bcefg";
        System.out.println("-------删除测试-------str=" + delStr4);
        System.out.println(customTrie.delete(delStr4));
        System.out.println(customTrie);
    }
}