package com.ibm.nfvodriver.driver;

import org.etsi.sol005.packagemanagement.NsPkgInfo;
import org.springframework.core.io.Resource;

import java.util.List;

public interface NSPackageRepositoryDriver {

    Resource getNsPackage(String nsPackageId) throws NSPackageNotFoundException;

    List<NsPkgInfo> queryAllNsPkgInfos(String groupName);

    NsPkgInfo getNsPkgInfo(String nsPackageId) throws NSPackageNotFoundException;

}
