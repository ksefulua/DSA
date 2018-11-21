class Launch {
    public static void main(String[] args) {
        String file = "test.txt";
        Generator generator = new Generator (15);
        Writer writer = new Writer (generator, file);
        //writer.writeOnFile_1();
        //writer.writeOnfile_2();
        //writer.writeOnFile_3();
        writer.writeOnfile_4(4, 0); // B, index
        Reader reader = new Reader ("ecrireContenuBuffer.txt");
        //reader.readFile_1 ();
        //reader.readFile_2 ();
        //reader.readFile_3();
        //reader.readFile_1 ();
        reader.readFile_4();
        reader.test_si_sur_la_lecture ("ecrireContenuMapper.txt");
    }
}