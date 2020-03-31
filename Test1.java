给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
class Solution {
    private List<String> res=new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        int len=s.length();
        if(len==0) return res;
        if(len<4||len>12){
            return res;
        }
        dfs(s,0,new ArrayList<>(),len,4);
        return res;
    }

    private void dfs(String s,int start,List<String> row,int len,int count){
        if(start==len){
            if(count==0){
                res.add(String.join(".",row));
            }
            return;
        }
        for(int i=start;i<start+3;i++){
            if(i>=len){
                break;
            }
            if(len-i>3*count){
                continue;
            }
            if(isVaild(s,start,i)){
                row.add(s.substring(start,i+1));
                dfs(s,i+1,row,len,count-1);
                row.remove(row.size()-1);
            }
        }
    }
    private boolean isVaild(String s,int left,int right){
        if((right-left+1)>1&&s.charAt(left)=='0'){
            return false;
        }
        int ret=0;
        for(int i=left;i<=right;i++){
            ret=ret*10+s.charAt(i)-'0';
        }
        return ret<=255&&ret>=0;
    }
}




给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

返回 s 所有可能的分割方案。
class Solution {
    List<List<String>> res=new ArrayList<>();
    public List<List<String>> partition(String s) {
        int len=s.length();
        if(len==0) return res;
        backTrack(s,0,len-1,new LinkedList<>());
        return res;
    }
    private void backTrack(String s,int left,int right,LinkedList<String> row){
        if(left>right){
            res.add(new ArrayList(row));
        }
        for(int i=left;i<=right;i++){
            if(isPaild(s.substring(left,i+1))){
                row.add(s.substring(left,i+1));
                backTrack(s,i+1,right,row);
                row.removeLast();
            }
        }
    }
    private boolean isPaild(String t){
        int i=0;
        int j=t.length()-1;
        while(i<j){
            if(t.charAt(i)!=t.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}

找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
class Solution {
    private List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        backTrack(n,k,1,new LinkedList<>());
        return res;
    }
    private void backTrack(int target,int k,int index,LinkedList<Integer> row){
        if(target<0){
            return;
        }
        if(target==0&&k==0){
            res.add(new ArrayList(row));
            return;
        }
        for(int i=index;i<=9;i++){
            row.add(i);
            backTrack(target-i,k-1,i+1,row);
            row.removeLast();
        }
    }
}

