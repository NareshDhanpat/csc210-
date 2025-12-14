package dna;

import java.util.ArrayList;
import java.util.HashMap;

public class DNACompare {

    // Convert DNA string to ArrayList of codons
    public static ArrayList<String> DNAToCodons(String dna) {
        ArrayList<String> codons = new ArrayList<>();
        dna = dna.toUpperCase().replaceAll("[^ACGT]", ""); // clean input
        for (int i = 0; i + 2 < dna.length(); i += 3) {
            codons.add(dna.substring(i, i + 3));
        }
        return codons;
    }

    // Convert codon to amino acid single-letter code
    public static String CodonToAminoAcid(String codon) {
        HashMap<String, String> map = new HashMap<>();
        map.put("TTT", "F"); map.put("TTC", "F");
        map.put("TTA", "L"); map.put("TTG", "L");
        map.put("CTT", "L"); map.put("CTC", "L"); map.put("CTA", "L"); map.put("CTG", "L");
        map.put("ATT", "I"); map.put("ATC", "I"); map.put("ATA", "I");
        map.put("ATG", "M");
        map.put("GTT", "V"); map.put("GTC", "V"); map.put("GTA", "V"); map.put("GTG", "V");
        map.put("TCT", "S"); map.put("TCC", "S"); map.put("TCA", "S"); map.put("TCG", "S");
        map.put("CCT", "P"); map.put("CCC", "P"); map.put("CCA", "P"); map.put("CCG", "P");
        map.put("ACT", "T"); map.put("ACC", "T"); map.put("ACA", "T"); map.put("ACG", "T");
        map.put("GCT", "A"); map.put("GCC", "A"); map.put("GCA", "A"); map.put("GCG", "A");
        map.put("TAT", "Y"); map.put("TAC", "Y");
        map.put("TAA", "Stop"); map.put("TAG", "Stop"); map.put("TGA", "Stop");
        map.put("CAT", "H"); map.put("CAC", "H");
        map.put("CAA", "Q"); map.put("CAG", "Q");
        map.put("AAT", "N"); map.put("AAC", "N");
        map.put("AAA", "K"); map.put("AAG", "K");
        map.put("GAT", "D"); map.put("GAC", "D");
        map.put("GAA", "E"); map.put("GAG", "E");
        map.put("TGT", "C"); map.put("TGC", "C");
        map.put("TGG", "W");
        map.put("CGT", "R"); map.put("CGC", "R"); map.put("CGA", "R"); map.put("CGG", "R");
        map.put("AGA", "R"); map.put("AGG", "R");
        map.put("AGT", "S"); map.put("AGC", "S");
        map.put("GGT", "G"); map.put("GGC", "G"); map.put("GGA", "G"); map.put("GGG", "G");

        return map.getOrDefault(codon, "?");
    }

    // Convert DNA string to amino acid sequence
    public static ArrayList<String> dnaToAminoAcid(String dna) {
        ArrayList<String> codons = DNAToCodons(dna);
        ArrayList<String> aminoAcids = new ArrayList<>();
        for (String codon : codons) {
            aminoAcids.add(CodonToAminoAcid(codon));
        }
        return aminoAcids;
    }

    // Compare amino acid sequences
    public static boolean isMatch(ArrayList<String> seq1, ArrayList<String> seq2) {
        if (seq1.size() != seq2.size()) return false;
        for (int i = 0; i < seq1.size(); i++) {
            if (!seq1.get(i).equals(seq2.get(i))) return false;
        }
        return true;
    }

    // MAIN PROGRAM
    public static void main(String[] args) {
        String DNA1 = "CTGATATTGTATCCGGCCGAA";
        String DNA2 = "CTAGCCGGTGGTTATTAATAGTAAACTATTCCA";
        String DNA3 = "TTAATCCTCTACCCCGCAGAG";

        ArrayList<String> seq1 = dnaToAminoAcid(DNA1);
        ArrayList<String> seq2 = dnaToAminoAcid(DNA2);
        ArrayList<String> seq3 = dnaToAminoAcid(DNA3);

        System.out.println("DNA1 vs DNA2: " + isMatch(seq1, seq2));
        System.out.println("DNA1 vs DNA3: " + isMatch(seq1, seq3));
        System.out.println("DNA2 vs DNA3: " + isMatch(seq2, seq3));
    }
}