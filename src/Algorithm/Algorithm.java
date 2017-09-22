/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author banhm
 */
public class Algorithm {
    
    private BigInteger n, d, e;
    
    public BigInteger getN() {
        return n;
    }
    
    public void setN(BigInteger n) {
        this.n = n;
    }
    
    public BigInteger getD() {
        return d;
    }
    
    public void setD(BigInteger d) {
        this.d = d;
    }
    
    public BigInteger getE() {
        return e;
    }
    
    public void setE(BigInteger e) {
        this.e = e;
    }

    /**
     * Create an instance that can both encrypt and decrypt.
     */
    public Algorithm() {
    }

    /**
     * create an instance that can encrypt using someone elses public key
     * @param n
     * @param e
     
     */
    public Algorithm(BigInteger n, BigInteger e) {
        this.n = n;
        this.e = e;
    }
    
    public void keyRSA(int bits) {
        SecureRandom rd = new SecureRandom();
        BigInteger p = new BigInteger(bits, 100, rd);
        BigInteger q = new BigInteger(bits, 100, rd);
        
        n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(
                q.subtract(BigInteger.ONE));//ϕ(n) = (p - 1)(q - 1) 
        boolean found = false;
        do {
            e = new BigInteger(bits , 50, rd);
            if (m.gcd(e).equals(BigInteger.ONE) && e.compareTo(m) < 0) {
                found = true;
            }
        } while (!found);
        d = e.modInverse(m);
        System.err.println(""+p);
        
    }
    //static BigInteger genPseudoPrime(int bits, int confidence, Random rand);
    /**
     * Encrypt the given plaintext message.
     * @param message
     * @return 
     */
    // E RSA
    public synchronized String encrypt(String message){
        //m = m^d mod n
        return (new BigInteger(message.getBytes())).modPow(d, n).toString();
    }
      /**
     * Encrypt the given plaintext message.
     * @param message
     * @return 
     */
    // ky so rsa
    public  synchronized BigInteger encrypt(BigInteger message){
        return  message.modPow(d, n);
    }
    /**
     * Decrypt the given ciphertext message.
     * @param message
     * @return 
     */
    //D DSRSA
    public synchronized String decrypt(String message){
        return new String((new BigInteger(message)).modPow(e, n).toByteArray());
    }
    /**
     * Decrypt the given ciphertext message.
     * @param message
     * @return 
     */
    // D RSA
    public synchronized BigInteger decrypt(BigInteger message){
        return message.modPow(e, n);
    }
    
    void setN(int bitleg) {
    }
    
}
