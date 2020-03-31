累加数是一个字符串，组成它的数字可以形成累加序列。

一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。

给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。

说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/additive-number
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public boolean isAdditiveNumber(String num) {
        int len=num.length();
        return backTrack(num,0,len,new LinkedList<>());
    }
    private boolean backTrack(String num,int begin,int len,LinkedList<String> tmp){
        if(begin==len&&tmp.size()>2){
            return true;
        }
        for(int i=begin;i<len;i++){
            String s=num.substring(begin,i+1);
            if(s.length()>1&&s.charAt(0)=='0'){
                return false;
            }
            int size=tmp.size();
            if(size<2||s.equals(sum(tmp.get(size-1),tmp.get(size-2)))){
                tmp.add(s);
                if(backTrack(num,i+1,len,tmp)){
                    return true;
                }
                tmp.removeLast();
            }
        }
        return false;
    }
    private String sum(String a,String b){
        StringBuilder sb=new StringBuilder();
        int carry=0;
        for(int i=a.length()-1,j=b.length()-1;i>=0||j>=0||carry!=0;){
            int m=i>=0?a.charAt(i)-'0':0;
            int n=j>=0?b.charAt(j)-'0':0;
            int k=m+n+carry;
            sb.append(k%10);
            carry=k/10;
            i--;
            j--;
        }
        return new String(sb.reverse());
    }
}

