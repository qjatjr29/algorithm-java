package leetcode.twoPointer;

public class AddTwoNumbers {

  private static ListNode answer;
  private static int upStreamNumber;

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

    answer = new ListNode();
    int v1 = l1.val;
    int v2 = l2.val;
    int s = v1 + v2;

    upStreamNumber = s / 10;
    answer.val = s % 10;
    ListNode temp;
    temp = answer;
    ListNode l1Next = l1.next;
    ListNode l2Next = l2.next;
    while(true) {

      if(l1Next == null || l2Next == null) break;
      temp = calc(temp, l1Next, l2Next);
      l1Next = l1Next.next;
      l2Next = l2Next.next;
    }

    if(l1Next == null) {
      while(true) {
        if(l2Next == null) break;
        int nValue = l2Next.val + upStreamNumber;
        upStreamNumber = nValue / 10;
        int nextNumber = nValue % 10;
        ListNode nextNode = new ListNode(nextNumber);
        temp.next = nextNode;
        temp =nextNode;
        l2Next = l2Next.next;
      }
    } else {
      while(true) {
        if(l1Next == null) break;
        int nValue = l1Next.val + upStreamNumber;
        upStreamNumber = nValue / 10;
        int nextNumber = nValue % 10;
        ListNode nextNode = new ListNode(nextNumber);
        temp.next = nextNode;
        temp =nextNode;
        l1Next = l1Next.next;
      }
    }
    if(upStreamNumber != 0) {
      temp.next = new ListNode(upStreamNumber);
    }

    return answer;
  }

  private ListNode calc(ListNode ans, ListNode l1, ListNode l2) {
    // 더하는 과정 필요
    int value1 = l1.val;
    int value2 = l2.val;

    int sum = value1 + value2 + upStreamNumber;
    upStreamNumber = sum / 10;
    int nextNumber = sum % 10;

    ListNode nextNode = new ListNode(nextNumber);

    ans.next = nextNode;

    return nextNode;
  }

  public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
