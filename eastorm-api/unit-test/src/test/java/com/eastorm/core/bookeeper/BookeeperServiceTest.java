//package com.eastorm.core.bookeeper;
//
//import org.apache.bookkeeper.client.BKException;
//import org.apache.bookkeeper.client.BookKeeper;
//import org.apache.bookkeeper.client.LedgerEntry;
//import org.apache.bookkeeper.client.LedgerHandle;
//import org.apache.zookeeper.KeeperException;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.nio.ByteBuffer;
//import java.util.Enumeration;
//
///**
// * <一句话功能简述>
// * <功能详细描述>
// *
// * @auth:江东大人
// * @see: [相关类/方法]（可选）
// * @since [产品/模块版本] （可选）
// */
//public class BookeeperServiceTest {
//    @Test
//    public void createLedger() throws InterruptedException, IOException, KeeperException, BKException {
//        // Create a client object for the local ensemble. This
//// operation throws multiple exceptions, so make sure to
//// use a try/catch block when instantiating client objects.
//        BookKeeper bkc = new BookKeeper("localhost:2181");
//
//// A password for the new ledger
//        byte[] ledgerPassword = new byte[]{1,2,3,4};/* some sequence of bytes, perhaps random */;
//
//// Create a new ledger and fetch its identifier
//        LedgerHandle lh = bkc.createLedger(BookKeeper.DigestType.MAC, ledgerPassword);
//        long ledgerId = lh.getId();
//
//// Create a buffer for four-byte entries
//        ByteBuffer entry = ByteBuffer.allocate(4);
//
//        int numberOfEntries = 100;
//
//// Add entries to the ledger, then close it
//        for (int i = 0; i < numberOfEntries; i++){
//            entry.putInt(i);
//            entry.position(0);
//            //lh.addEntry(entry.array());
//        }
//        lh.close();
//
//        System.out.println("ledgerId:"+ledgerId);ledgerId=10;
//// Open the ledger for reading
//        lh = bkc.openLedger(ledgerId, BookKeeper.DigestType.MAC, ledgerPassword);
//
//// Read all available entries
//        Enumeration<LedgerEntry> entries = lh.readEntries(50, numberOfEntries - 1);
//
//        while(entries.hasMoreElements()) {
//            ByteBuffer result = ByteBuffer.wrap(entries.nextElement().getEntry());
//            Integer retrEntry = result.getInt();
//
//            // Print the integer stored in each entry
//            System.out.println(String.format("Result: %s", retrEntry));
//        }
//
//// Close the ledger and the client
//        lh.close();
//        bkc.close();
//    }
//}
