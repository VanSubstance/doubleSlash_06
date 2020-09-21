package com.example.temporal;

public interface OnItemClickForFunding {
    void onClick(fundingItem newOne);
    //void onItemClick(int position);
}
//    void onClick(fundingItem newOne);
//                Button buttonFunding = itemView.findViewById(R.id.funding_button);
//
//            buttonFunding.setOnClickListener(new Button.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(fundingItem.this());
//                    builder.setTitle("펀딩이 완료되었습니다!");
//                    builder.setMessage("펀딩 완료 메시지");
//                    builder.setPositiveButton("확인",new DialogInterface.OnClickListener(){
//                        public void onClick(DialogInterface dialog, int which) {
//                            //토스트 메시지
//                            Toast.makeText(getActivity(),"펀딩 완료",Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//                    //builder.setNeutralButton("취소", null);
//                    builder.show();
//                }
//            });

