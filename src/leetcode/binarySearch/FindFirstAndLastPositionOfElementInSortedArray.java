package leetcode.binarySearch;

public class FindFirstAndLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {

        int sIndex = startIndex(nums, target);
        int eIndex = endIndex(nums, target);

        return new int[] {sIndex, eIndex};
    }

    public int startIndex(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        int index = -1;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                // 가장 첫번째 인덱스를 구함.
                if(mid == 0 || nums[mid - 1] != target) {
                    index = mid;
                    break;
                }
                right = mid - 1;
            }
            if(nums[mid] < target) {
                left = mid + 1;
                continue;
            }
            right = mid - 1;
        }
        return index;
    }

    public int endIndex(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        int index = -1;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                if(mid == nums.length - 1 || nums[mid + 1] != target) {
                    index = mid;
                    break;
                }
                left = mid + 1;
                continue;
            }
            if(nums[mid] < target) {
                left = mid + 1;
                continue;
            }
            right = mid - 1;
        }
        return index;
    }


}
