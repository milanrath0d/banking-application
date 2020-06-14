package org.milan.banking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Head office of the bank. Responsible for creating and keeping track of all the branches
 *
 * @author Milan Rathod
 */
public class HeadOffice {

    private List<Branch> branches = new ArrayList<>();

    private static AtomicInteger branchId = new AtomicInteger();

    /**
     * Create Branch with auto generated branchId
     */
    public void createBranch() {
        branches.add(new Branch(String.valueOf(branchId.addAndGet(1))));
    }

    /**
     * Get branch by branchId
     *
     * @param branchId branchId
     * @return If branch with branchId exists then return branch otherwise null
     */
    public Branch getBranchById(String branchId) {
        return branches.stream().filter(branch -> branch.getBranchId().equals(branchId)).findFirst().orElse(null);
    }

    /**
     * Get list of all branches
     */
    public List<Branch> getAllBranches() {
        return branches;
    }
}
