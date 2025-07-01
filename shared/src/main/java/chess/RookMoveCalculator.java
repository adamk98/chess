package chess;

import java.util.ArrayList;
import java.util.Collection;

//class
public class RookMoveCalculator implements PieceMovesCalculator {
    private final ChessGame.TeamColor teamColor;

    //constructor
    public RookMoveCalculator(ChessGame.TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    //i need to
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        // Rooks may move in straight lines as far as there is open space. If there is an
        // enemy piece at the end of the line, rooks may move to that position and capture
        // the enemy piece
        Collection<ChessMove> theMoves = new ArrayList<>();

        //So smart, this repeats all the moves.
        theMoves.addAll(repeatMoves(board, myPosition, 0, 1));
        theMoves.addAll(repeatMoves(board, myPosition, 0, -1));
        theMoves.addAll(repeatMoves(board, myPosition, 1, 0));
        theMoves.addAll(repeatMoves(board, myPosition, -1, 0));

        return theMoves;

    }

    private Collection<ChessMove> repeatMoves(ChessBoard board, ChessPosition myPosition, int rowMod, int colMod){
        Collection<ChessMove> theMoves = new ArrayList<>();

        ChessPiece myPiece = board.getPiece(myPosition);

        ChessPosition testPosition = new ChessPosition(myPosition.getRow() + rowMod, myPosition.getColumn() + colMod);
        ChessPiece isPiece;
        //test the ROOK going to the right
        while(testPosition.getColumn() <= 8 && testPosition.getColumn() >= 1 && testPosition.getRow() <=8 && testPosition.getRow() >=1){
            //if space on board is empty, that's a possible move, so add it to collection
            //PUT IN WHILE LOOP
            isPiece = board.getPiece(testPosition);
            if(isPiece == null){
                theMoves.add(new ChessMove(myPosition, testPosition, null));


                testPosition = new ChessPosition(testPosition.getRow() + rowMod, testPosition.getColumn() + colMod);
                //isPiece = board.getPiece(testPosition);

            } else if(isPiece.getTeamColor() != myPiece.getTeamColor()) {
                //when in while loop use break. because the while loop is automatically adding each possible moves to the
                //Collection of moves (Collection<ChessMove> theMoves = new ArrayList<>();) line 21 or around if code changes
                //and we don't want to add possible location 'myPiece' can move to.
                theMoves.add(new ChessMove(myPosition, testPosition, null));
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

