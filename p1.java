import java.util.ArrayList;
import java.util.List;

class CHECK_NAME extends Exception {
    public CHECK_NAME(String m) {
        super(m);
    }
}

class CHECK_JOURNAL_ID extends Exception {
    public CHECK_JOURNAL_ID(String m) {
        super(m);
    }
}

class ISSUENUMBER extends Exception {
    public ISSUENUMBER(String m) {
        super(m);
    }
}

class CHECK_ISSN extends Exception {
    public CHECK_ISSN(String m) {
        super(m);
    }
}

class Journal {
    private String name;
    private String journalID;
    private String issueNumber;
    private String issn;

    public Journal(String name, String journalID, String issueNumber, String issn) {
        this.name = name;
        this.journalID = journalID;
        this.issueNumber = issueNumber;
        this.issn = issn;
    }

    @Override
    public String toString() {
        return "Journal [Name=" + name + ", JournalID=" + journalID +
                ", IssueNumber=" + issueNumber + ", ISSN=" + issn + "]";
    }
}

class p1 {
    public static void validateName(String nm) throws CHECK_NAME {
        if (nm.length() > 30) {
            throw new CHECK_NAME("Invalid Name: Must be <= 30 characters.");
        }
        for (char ch : nm.toCharArray()) {
            if (!Character.isLetter(ch))
                throw new CHECK_NAME("Invalid Name: Must contain alphabets only.");
        }
    }

    public static void validateJournalID(String id) throws CHECK_JOURNAL_ID {
        for (char ch : id.toCharArray()) {
            if (!Character.isLetterOrDigit(ch))
                throw new CHECK_JOURNAL_ID("Invalid Journal ID: Must be alphanumeric.");
        }
    }

    public static void validateIssueNumber(String in) throws ISSUENUMBER {
        if (in.length() > 20)
            throw new ISSUENUMBER("Invalid Issue Number: Must be <= 20 characters.");
    }

    public static void validateISSN(String issn) throws CHECK_ISSN {
        if (issn.length() != 9) {
            throw new CHECK_ISSN("Invalid ISSN: Must be exactly 9 characters including one hyphen.");
        }

        int hyphenCount = 0;
        for (char ch : issn.toCharArray()) {
            if (ch == '-') {
                hyphenCount++;
            } else if (!Character.isDigit(ch)) {
                throw new CHECK_ISSN("Invalid ISSN: Must contain only digits and one hyphen.");
            }
        }
        if (hyphenCount != 1) {
            throw new CHECK_ISSN("Invalid ISSN: Must contain exactly one hyphen.");
        }
    }

    public static void main(String[] args) {
        List<Journal> journals = new ArrayList<>();
        String[][] journalDetails = {
                { "JournalOne", "J12345", "001", "1234-56789" }, // Invalid ISSN
                { "AnotherJournal", "AJ7890", "002", "9876-5432" },
                { "InvalidName123", "ID001", "003", "1111-2222" }, // Invalid Name
                { "ValidJournal", "ID@002", "004", "3333-4444" }, // Invalid ID
                { "LongJournalNameWhichExceedsLimit", "ID003", "005", "5555-6666" } // Invalid Name
        };

        for (String[] details : journalDetails) {
            try {
                validateName(details[0]);
                validateJournalID(details[1]);
                validateIssueNumber(details[2]);
                validateISSN(details[3]);
                journals.add(new Journal(details[0], details[1], details[2], details[3]));
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("\nValid Journals:");
        for (Journal journal : journals) {
            System.out.println(journal);
        }
    }

}