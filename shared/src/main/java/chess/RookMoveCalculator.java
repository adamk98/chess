package chess;

import java.util.ArrayList;
import java.util.Collection;


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
        Collection<ChessMove> rookMoves = new ArrayList<>();
        //repeats repeatPosition 4 times.
        rookMoves.addAll(repeatPosition(board, myPosition, 0, 1));
        rookMoves.addAll(repeatPosition(board, myPosition, 0, -1));
        rookMoves.addAll(repeatPosition(board, myPosition, 1, 0));
        rookMoves.addAll(repeatPosition(board, myPosition, -1, 0));

        return rookMoves;
    }


    private Collection<ChessMove> repeatPosition(ChessBoard board, ChessPosition myPosition, int rowMod, int colMod){

        Collection<ChessMove> rookMoves = new ArrayList<>();
        //get position of Piece at myPosition which is the starting location of the rook. store it in myPiece
        //What is ChessPiece: ChessPiece:PieceColor,type. PieceColor -> ChessGame:TeamColor (in ChessPiece it's ChessGame.TeamColor PieceColor). type -> ChessPiece.PieceType type. It self-references itself.
        ChessPiece myPiece = board.getPiece(myPosition);
        //what is board: board is datatype ChessBoard is a parameter passed into repeatPosition method of datatype ChessBoard. Attributes of ChessBoard is 2D array called squares. in ChessBoard there is a method called getPiece that gets the location of a piece
        //**QUESTION: ChessPiece has attributes of pieceColor and type. So how come its able to take a position? ANSWER its getting the type of piece at the position from myPosition lol. got it.
        //myPosition comes from RookMoveTest where it says 'new ChessPosition(2, 3);' which is passed into ChessPosition row and col, and then putting ChessPosition myPosition.

        ChessPosition testPosition = new ChessPosition(myPosition.getRow() + rowMod, myPosition.getColumn() + colMod);
        ChessPiece isPiece;

        while(testPosition.getColumn() <= 8 && testPosition.getColumn() >= 1 && testPosition.getRow() <=8 && testPosition.getRow() >=1){
            //if space on board is empty, that's a possible move, so add it to collection
            //testposition is checking what data is stored in one 'makeBelieve' square to the right of my starting position given to me from RookMoveTest position.
            isPiece = board.getPiece(testPosition);
            if(isPiece == null){
                rookMoves.add(new ChessMove(myPosition, testPosition, null));

                testPosition = new ChessPosition(testPosition.getRow() + rowMod, testPosition.getColumn() + colMod);

            } else if(isPiece.getTeamColor() != myPiece.getTeamColor()) {
                rookMoves.add(new ChessMove(myPosition, testPosition, null));
                //break is only used for loops
                break;
            }else {
                //the only other option is if ispiece and mypiece are the same color so else covers that.
                //break out of loop
                break;
            }
        }
        return rookMoves;
    }
}

