package chess;

import java.util.ArrayList;
import java.util.Collection;

//**QUESTION what does this mean?
public class RookMoveCalculator implements PieceMovesCalculator {
    private final ChessGame.TeamColor teamColor;

    //constructor
    public RookMoveCalculator(ChessGame.TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        // Rooks may move in straight lines as far as there is open space. If there is an
        // enemy piece at the end of the line, rooks may move to that position and capture
        // the enemy piece
        Collection<ChessMove> theMoves = new ArrayList<>();
        //repeats repeatPosition 4 times.
        theMoves.addAll(repeatPosition(board, myPosition, 0, 1));
        theMoves.addAll(repeatPosition(board, myPosition, 0, -1));
        theMoves.addAll(repeatPosition(board, myPosition, 1, 0));
        theMoves.addAll(repeatPosition(board, myPosition, -1, 0));

        return theMoves;
    }

    //I'll walk you through code tell me where I am going wrong
    //repeat position method uses a while loop to go through code.

    private Collection<ChessMove> repeatPosition(ChessBoard board, ChessPosition myPosition, int rowMod, int colMod){

        Collection<ChessMove> theMoves = new ArrayList<>();
        //get position of Piece at myPosition which is the starting location of the rook. store it in myPiece
        ChessPiece myPiece = board.getPiece(myPosition);

        ChessPosition testPosition = new ChessPosition(myPosition.getRow() + rowMod, myPosition.getColumn() + colMod);
        ChessPiece isPiece;
        //test the ROOK going to the right
        while(testPosition.getColumn() <= 8 && testPosition.getColumn() >= 1 && testPosition.getRow() <=8 && testPosition.getRow() >=1){
            //if space on board is empty, that's a possible move, so add it to collection
            isPiece = board.getPiece(testPosition);
            if(isPiece == null){
                theMoves.add(new ChessMove(myPosition, testPosition, null));

                testPosition = new ChessPosition(testPosition.getRow() + rowMod, testPosition.getColumn() + colMod);

            } else if(isPiece.getTeamColor() != myPiece.getTeamColor()) {
                theMoves.add(new ChessMove(myPosition, testPosition, null));
                //break is only used for loops
                break;
            }else {
                //the only other option is if ispiece and mypiece are the same color so else covers that.
                //break out of loop
                break;
            }
        }
        return theMoves;
    }
}

